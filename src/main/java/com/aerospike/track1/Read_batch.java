package com.aerospike.track1;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.BatchPolicy;

public class Read_batch {

    public static void main(String[] args) {
        // Khởi tạo AerospikeClient
        AerospikeClient client = new AerospikeClient("localhost", 3000);

        // Khởi tạo BatchPolicy
        BatchPolicy batchPolicy = new BatchPolicy();
        batchPolicy.sendKey = true; // Thiết lập để gửi keys

        // Tạo mảng keys
        Key[] keys = new Key[10];
        for (int i = 0; i < 10; i++) {
            keys[i] = new Key("test", "demo", (i + 1));
        }

        // Đọc các bản ghi
        Record[] records = client.get(batchPolicy, keys);

        // Truy cập và xử lý các bản ghi
        for (Record record : records) {
            if (record != null) {
                System.out.format("Record: %s\n", record.bins);
            }
        }

        // Đóng kết nối tới server
        client.close();
    }
}
