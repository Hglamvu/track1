package com.aerospike.track1;
import com.aerospike.client.AerospikeClient;
import com.aerospike.client.query.Filter;
import com.aerospike.client.query.Statement;
import com.aerospike.client.query.ResultSet;
import com.aerospike.client.policy.QueryPolicy;
public class query {
        public static void main(String[] args) {
            // Thiết lập kết nối với cluster Aerospike
            AerospikeClient client = new AerospikeClient("127.0.0.1", 3000);

            // Thiết lập chính sách cho query
            QueryPolicy policy = new QueryPolicy();

            // Tạo câu lệnh query
            Statement stmt = new Statement();
            stmt.setNamespace("test");
            stmt.setSetName("demoSet");
            stmt.setFilter(Filter.range("age", 20, 30)); // Lọc các bản ghi có giá trị cột "age" trong khoảng từ 20 đến 30

            // Thực hiện query
            ResultSet rs = client.query(policy, stmt);

            // Xử lý kết quả query
            while (rs.next()) {
                System.out.println("Key: " + rs.getKey());
                System.out.println("Record: " + rs.getRecord());
            }

            // Đóng kết nối với cluster Aerospike
            client.close();
        }
}
