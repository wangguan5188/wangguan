package com.wangguan.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Map相关操作工具类
 * 遍历map的三种方式：
 * 1.遍历所有的key
 * 2.遍历所有的key-value对
 * 3.遍历所有的value（不常用）
 * @author zhangtb
 * @date 2015年8月3日16:32:56
 */
public class MapUtil {
	
	private static List<MapEntity> mapEntityList = new ArrayList<MapEntity>();
	
	/**
	 * 遍历所有的key
	 * @param map
	 * @return
	 */
	public static List<MapEntity> keySet(Map<String, Object> map) {
		Set<String> set = map.keySet();
		/*for(Iterator i = set.iterator();i.hasNext();) {
			String key = i.next();
			//System.out.println("key=" + key + " value=" + map.get(key));
			MapEntity mapEntity = new MapEntity();
			mapEntity.setKey(key);
			mapEntity.setValue(map.get(key));
			mapEntityList.add(mapEntity);
		}*/
		for(String key : set) {
			//System.out.println("key=" + key + " value=" + map.get(key));
			MapEntity mapEntity = new MapEntity();
			mapEntity.setKey(key);
			mapEntity.setValue(map.get(key));
			mapEntityList.add(mapEntity);
		}
		return mapEntityList;
	}
	
	/**
	 * 遍历所有的key-value对
	 * @param map
	 * @return
	 */
	public static List<MapEntity> entrySet(Map<String, Object> map) {
		/*Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Entry<String, Object>) it.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			//System.out.println("key=" + key + " value=" + value);
			MapEntity mapEntity = new MapEntity();
			mapEntity.setKey(key);
			mapEntity.setValue(value);
			mapEntityList.add(mapEntity);
		}*/
		for(Map.Entry<String, Object> entry : map.entrySet()){  
			String getKey = entry.getKey();  
			Object getValue = entry.getValue();  
            //System.out.println("the map key is : " + getKey + " || the value is : " + getValue);
            MapEntity mapEntity = new MapEntity();
            mapEntity.setKey(getKey);
            mapEntity.setValue(getValue);
            mapEntityList.add(mapEntity);
        }
		return mapEntityList;
	}
	
	/**
	 * 遍历所有的value（不常用）
	 * @param map
	 * @return
	 */
	public static List<MapEntity> values(Map<String, Object> map) {
		Collection<Object> c = map.values();  
        for(Object obj : c) {
        	//System.out.println(obj);
        	MapEntity mapEntity = new MapEntity();
        	mapEntity.setValue(obj);
        	mapEntityList.add(mapEntity);
        }
        return mapEntityList;
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("测试key222", 222);
		map.put("测试key333", 333);
		map.put("测试key555", 555);
		List<MapEntity> mapEntityList1 = MapUtil.keySet(map);
		List<MapEntity> mapEntityList2 = MapUtil.entrySet(map);
		List<MapEntity> mapEntityList3 = MapUtil.values(map);
		for(MapEntity m : mapEntityList1) {
			System.out.println(m);
		}
		for(MapEntity m : mapEntityList2) {
			System.out.println(m);
		}
		for(MapEntity m : mapEntityList3) {
			System.out.println(m);
		}
	}

}
