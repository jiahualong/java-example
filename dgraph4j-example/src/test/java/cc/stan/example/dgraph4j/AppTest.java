package cc.stan.example.dgraph4j;


import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.dgraph.DgraphProto;
import io.dgraph.Transaction;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

public class AppTest {


    private DgraphClient createDgraphClient(boolean withAuthHeader) {
        ManagedChannel channel =
                ManagedChannelBuilder.forAddress("localhost", 9080).usePlaintext(true).build();
        DgraphGrpc.DgraphStub stub = DgraphGrpc.newStub(channel);
        if (withAuthHeader) {
            Metadata metadata = new Metadata();
            metadata.put(
                    Metadata.Key.of("auth-token", Metadata.ASCII_STRING_MARSHALLER), "the-auth-token-value");
            stub = MetadataUtils.attachHeaders(stub, metadata);
        }
        return new DgraphClient(stub);
    }

    @Test
    public void cleanDB() {
        DgraphClient dgraphClient = createDgraphClient(false);
        dgraphClient.alter(DgraphProto.Operation.newBuilder().setDropAll(true).build());
    }

    @Test
    public void createIndex() {
        DgraphClient dgraphClient = createDgraphClient(false);
        String schema = "name: string @index(exact) .";
        DgraphProto.Operation op = DgraphProto.Operation.newBuilder().setSchema(schema).build();
        dgraphClient.alter(op);
    }

    @Test
    public void insert() {

        DgraphClient dgraphClient = createDgraphClient(false);
        Gson gson = new Gson(); // For JSON encode/decode
        Transaction txn = dgraphClient.newTransaction();

        try {
            // Create data
            Person p001 = new Person();
            p001.setPid("001");
            p001.setName("小风");

            Person p002 = new Person();
            p002.setPid("002");
            p002.setName("小雷");

            Person p003 = new Person();
            p003.setUid("003");
            p003.setPid("003");
            p003.setName("小雨");

            Person p = new Person();
            p.setName("Alice");

//
//            Call call001 = new Call();
//            call001.setFrom(p001);
//            call001.setTo(p002);
//            call001.setDatetime("2018-03-01 12:11:10");
//            call001.setLongTime(1000);
//
//            Call call002 = new Call();
//            call002.setFrom(p002);
//            call002.setTo(p001);
//            call002.setDatetime("2018-03-01 12:13:11");
//            call002.setLongTime(1000);
//
//            Call call003 = new Call();
//            call003.setFrom(p002);
//            call003.setTo(p003);
//            call003.setDatetime("2018-03-01 12:14:11");
//            call003.setLongTime(1000);
//
//            Call call004 = new Call();
//            call004.setFrom(p003);
//            call004.setTo(p001);
//            call004.setDatetime("2018-03-01 12:14:11");
//            call004.setLongTime(1000);
//
//            p001.setCalls(Collections.singletonList(call001));
//            p002.setCalls(Arrays.asList(call002, call003));
//            p003.setCalls(Collections.singletonList(call004));


            // Serialize it
            String json001 = gson.toJson(p001);
            String json002 = gson.toJson(p002);
            String json003 = gson.toJson(p003);
//            String jsoncall001 = gson.toJson(call001);
//            String jsoncall002 = gson.toJson(call002);
//            String jsoncall003 = gson.toJson(call003);
//            String jsoncall004 = gson.toJson(call004);

            // Run mutation
            DgraphProto.Mutation mu =
                    DgraphProto.Mutation.newBuilder().setSetJson(ByteString.copyFromUtf8(json003.toString())).build();
            txn.mutate(mu);
//            mu = DgraphProto.Mutation.newBuilder().setSetJson(ByteString.copyFromUtf8(json002.toString())).build();
//            txn.mutate(mu);
//            mu = DgraphProto.Mutation.newBuilder().setSetJson(ByteString.copyFromUtf8(json003.toString())).build();
//            txn.mutate(mu);
//            mu = DgraphProto.Mutation.newBuilder().setSetJson(ByteString.copyFromUtf8(jsoncall002.toString())).build();
//            txn.mutate(mu);
//            mu = DgraphProto.Mutation.newBuilder().setSetJson(ByteString.copyFromUtf8(jsoncall003.toString())).build();
//            txn.mutate(mu);
//            mu = DgraphProto.Mutation.newBuilder().setSetJson(ByteString.copyFromUtf8(jsoncall004.toString())).build();
//            txn.mutate(mu);
            txn.commit();

        } finally {
            txn.discard();
        }


    }

    @Test
    public void query() {

        DgraphClient dgraphClient = createDgraphClient(false);
        Gson gson = new Gson(); // For JSON encode/decode

        // Query
        String query =
        "query all($a:string) {\n" +
                "  me(func:eq(name, $a)) {\n" +
                "    uid\n" +
                "    name\n" +
                "    pid\n" +
                "  }\n" +
                "}\n";
        Map<String, String> vars = Collections.singletonMap("$a", "小风");
        DgraphProto.Response res = dgraphClient.newTransaction().queryWithVars(query, vars);

        System.out.println(res.getJson().toStringUtf8());

        // Deserialize
        Person person = gson.fromJson(res.getJson().toStringUtf8(), Person.class);

        // Print results

    }


}
