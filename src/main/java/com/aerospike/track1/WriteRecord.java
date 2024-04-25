package com.aerospike.track1;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.policy.WritePolicy;

public class WriteRecord {
    public static void main(String[] args) {
        // Khởi tạo AerospikeClient
        AerospikeClient client = new AerospikeClient("localhost", 3000);

        // Khởi tạo Key
        Key key1 = new Key("test", "demo", "key_value");

        // Khởi tạo các Bin
        Bin bin1 = new Bin("name", "Nguyen Van B");
        Bin bin2 = new Bin("age", 22);
        Bin bin3 = new Bin("greeting", "Xin chao");


        // Thời gian tồn tại của bản ghi (expire time) - 1 giờ (3600 giây)
        int expireTimeSeconds = 3600;

        // Thiết lập policy cho việc ghi bản ghi với expire time
        WritePolicy writePolicy = new WritePolicy();
        writePolicy.expiration = expireTimeSeconds;

        // Ghi một bản ghi vào Aerospike với expire time
        client.put(writePolicy, key1, bin1, bin2, bin3);
//        client.put(writePolicy, key2, bin4, bin5, bin6);
        System.out.println("Successfully written the record with expiration.");

        // Đóng kết nối đến Aerospike
        client.close();
    }
}
