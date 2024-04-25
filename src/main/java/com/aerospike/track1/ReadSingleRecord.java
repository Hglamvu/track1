package com.aerospike.track1;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;

public class ReadSingleRecord {
    public static void main(String [] args){
        AerospikeClient client = new AerospikeClient("localhost", 3000);

        Key key = new Key("test","demo", "key_value");

        Record record = client.get(null, key);
        if(record!=null){
            System.out.println("Record found:");
            //Get thủ công nhiều lần
            System.out.println("Name: " + record.getValue("name"));
            System.out.println("Age: " + record.getValue("age"));
            System.out.println("Greeting: " + record.getValue("greeting"));
        }
         else{
            System.out.println("No record founded!");
        }
    client.close();
    }
}
