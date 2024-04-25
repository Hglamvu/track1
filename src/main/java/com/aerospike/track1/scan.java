package com.aerospike.track1;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ScanPolicy;

public class scan {
    public static void main(String [] args){
        AerospikeClient client = new AerospikeClient("localhost", 3000);

        ScanPolicy scanPolicy = new ScanPolicy();
        client.scanAll(scanPolicy, "test", "demo", (key, record) -> {
            // Xử lý mỗi bản ghi được quét được ở đây
            System.out.println("Key: " + key);
            System.out.println("Record: " + record);
        });

        // Đóng kết nối với cluster Aerospike
        client.close();
    }
}

