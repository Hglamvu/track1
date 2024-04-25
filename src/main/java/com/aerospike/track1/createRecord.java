package com.aerospike.track1;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;

public class createRecord {
    public static void main(String [] args){
        AerospikeClient client = new AerospikeClient("localhost", 3000);
        try{
            Key key = new Key("test", "demo", "user_key");
            Bin bin1 = new Bin("a", 3);
            Bin bin2 = new Bin("b", "abc");
            Bin bin3 = new Bin("c", 5.4);
            client.put(null, key, bin1,bin2,bin3);
            System.out.println("Ghi ban doc thanh cong!");
        } catch (Exception e){
            System.err.println("Error: " + e.getMessage());

        } finally {
            client.close();
        }
    }
}
