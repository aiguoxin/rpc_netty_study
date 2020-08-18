/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.liyue2008.rpc.serialize.impl.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.liyue2008.rpc.nameservice.Metadata;
import com.github.liyue2008.rpc.serialize.Serializer;
import com.github.liyue2008.rpc.serialize.impl.Types;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** aiguoxin */
public class MetadataSerializer implements Serializer<Metadata> {

    @Override
    public int size(Metadata entry) {
        //todo 为什么需要+Integer.BYTES，改成Short.BYTES会报错
        return Integer.BYTES + JSON.toJSONString(entry).getBytes(StandardCharsets.UTF_8).length;
    }

    @Override
    public void serialize(Metadata entry, byte[] bytes, int offset, int length) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, length);
        byte[] valByte = JSON.toJSONString(entry).getBytes(StandardCharsets.UTF_8);
        buffer.putInt(valByte.length);
        buffer.put(valByte);
    }

    @Override
    public Metadata parse(byte[] bytes, int offset, int length) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, length);
        Metadata metadata = new Metadata();
        int sizeOfMap = buffer.getInt();
        byte[] valByte = new byte[sizeOfMap];
        buffer.get(valByte);

        String json = new String(valByte, StandardCharsets.UTF_8);
        Map<String, JSONArray> jsonRs = JSON.parseObject(json,Map.class);
        for(Map.Entry<String, JSONArray> entry:jsonRs.entrySet()){
            List<URI> list = new ArrayList<>();
            for(Object uri : entry.getValue()){
                list.add(URI.create(uri.toString()));
            }
            metadata.put(entry.getKey(), list);
        }

        return metadata;
    }

    @Override
    public byte type() {
        return Types.TYPE_METADATA;
    }

    @Override
    public Class<Metadata> getSerializeClass() {
        return Metadata.class;
    }
}
