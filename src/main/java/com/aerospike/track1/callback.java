package com.aerospike.track1;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.WritePolicy;

public class callback {
    public static void main(String[] args) {
        // Thiết lập cấu hình kết nối với cluster Aerospike
        ClientPolicy clientPolicy = new ClientPolicy();
        AerospikeClient client = new AerospikeClient(clientPolicy, "localhost", 3000);

        // Thiết lập Key cho bản ghi cần đọc
        String namespace = "test";
        String set = "demo";
        String keyString = "key1";
        Key key = new Key(namespace, set, keyString);

        // Đọc bản ghi từ cơ sở dữ liệu
        Record record = client.get(null, key);

        // Xử lý kết quả đọc thông qua callback
        handleReadResult(record);

        // Đóng kết nối với cluster Aerospike
        client.close();
    }

    // Callback để xử lý kết quả đọc
    public static void handleReadResult(Record record) {
        if (record == null) {
            System.out.println("Không tìm thấy bản ghi trong cơ sở dữ liệu.");
        } else {
            System.out.println("Dữ liệu được đọc từ cơ sở dữ liệu:");
            for (String binName : record.bins.keySet()) {
                Object value = record.bins.get(binName);
                System.out.println(binName + ": " + value);
            }
            // Gọi các phương thức xử lý dữ liệu khác ở đây nếu cần
        }
    }
}
