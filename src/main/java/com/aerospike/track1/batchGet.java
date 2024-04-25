package com.aerospike.track1;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.BatchPolicy;
import com.aerospike.client.policy.WritePolicy;

public class batchGet {
    public static void main(String[] args) {
        AerospikeClient client = new AerospikeClient("localhost", 3000);
        // Ghi dữ liệu giả định vào các record
        WritePolicy writePolicy = new WritePolicy();
        Key key1 = new Key("test", "demo", "key1");
        Key key2 = new Key("test", "demo", "key2");
        Key key3 = new Key("test", "demo", "key3");
        Bin bin1 = new Bin("name", "vu hoang lam");
        Bin bin2 = new Bin("name", "hoang van a");
        Bin bin3 = new Bin("name", "le van b");
        client.put(writePolicy, key1, bin1);
        client.put(writePolicy, key2, bin2);
        client.put(writePolicy, key3, bin3);

        // Đọc các record vừa ghi bằng batch
        BatchPolicy batchPolicy = new BatchPolicy();
        batchPolicy.sendKey = true;
        //thiết lập csách gửi khoá đảm bảo khoá gửi kèm với mỗi yêu cầu đọc batch
        Key[] keys = new Key[]{key1, key2, key3};
        Record[] records = client.get(batchPolicy, keys);

        // Xử lý kết quả
        for (Record record : records) {
            if (record != null) {//nếu tìm thấy record thì...
                System.out.println("Record found: " + record.getValue("name"));
            } else {
                System.out.println("Record not found");
            }
        }
        client.close();
    }
}
