package com.aerospike.track1;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;

public class MultipleRecords {

    public static void writeMultiple(AerospikeClient client, WritePolicy writePolicy ){
        //tạo các mảng chứa
        String [] keys = {"key1","key2","key3"};
        String [] name = {"vu hoang lam","nguyen hoang a", "le van b"};
        int age[] = {20,21,22};
        
        for(int i = 0; i < keys.length; i++){
            Key key = new Key("test", "demo", keys[i]);
            Bin bin1 = new Bin("name", name[i]);
            Bin bin2 = new Bin ("age", age[i]);
            client.put(writePolicy, key, bin1,bin2);
            System.out.println("Record with key " + keys[i] + " written successfully.");
        }
    }
    public static void readMultiple(AerospikeClient client){
        String[] keys = {"key1", "key2", "key3"};

        // Đọc từng bản ghi từ Aerospike
        for (String key : keys) {
            Key aerospikeKey = new Key("test", "demo", key);
            Record record = client.get(null, aerospikeKey);
            if (record != null) {
                System.out.println("Record found for " + key + ":");
                System.out.println("Name: " + record.getValue("name"));
                System.out.println("Age: " + record.getValue("age"));
            } else {
                System.out.println("No record found for " + key);
            }
        }
    }
    public static void main (String [] args){
        //kết nối
        AerospikeClient client = new AerospikeClient("localhost", 3000);
        WritePolicy writePolicy = new WritePolicy();
        writeMultiple(client, writePolicy);
        readMultiple(client);

        client.close();
    }
}
