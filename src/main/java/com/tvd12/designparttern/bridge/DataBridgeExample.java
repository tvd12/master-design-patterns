package com.tvd12.designparttern.bridge;

import java.util.HashMap;
import java.util.Map;

public class DataBridgeExample {

	public static void main(String[] args) {
		String databaseType = args.length > 0 ? args[0] : "MySQL";
		
		// create persistence API
		Persistence persistenceAPI = new PersistenceImp(databaseType);
		
		// save an entity
		persistenceAPI.persist(new UserEntity("foo", "Mr.Foo"));
		
		// get an entity
		UserEntity user = (UserEntity) persistenceAPI.findById("foo");
		System.out.println("findById(foo): " + user);
		
		// delete an entity
		persistenceAPI.deleteById("123456");
	}
	
}

class UserEntity implements Entity {
	
	private String id;
	private String fullName;
	
	public UserEntity(String id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	@Override
	public String toString() {
		return "User(" + id + ", " + fullName + ")";
	}
}

interface Entity {
	String getId();

	void setId(String id);
}

interface Persistence {
	
	public void persist(Entity entity);

	public Entity findById(String id);

	public void deleteById(String id);
}

// bridge interface
interface PersistenceImplementor {

	public void saveEntity(Entity entity);

	public void deleteEntity(String entityId);

	public Entity getEntity(String entityId);
}

class PersistenceImp implements Persistence {
	
	// bridge object
	private PersistenceImplementor implementor;
	
	public PersistenceImp(String databaseType) {
		this.implementor = databaseType.equals("MySQL")
				? new MySQLPersistenceImplementor()
				: new OraclePersistenceImplementor();
	}

	@Override
	public void persist(Entity entity) {
		implementor.saveEntity(entity);
	}

	@Override
	public Entity findById(String id) {
		return implementor.getEntity(id);
	}

	@Override
	public void deleteById(String id) {
		implementor.deleteEntity(id);
	}

}

class MySQLPersistenceImplementor implements PersistenceImplementor {

	private final Map<String, Entity> entities = new HashMap<>();
	
	@Override
	public void saveEntity(Entity entity) {
		entities.put(entity.getId(), entity);
	}

	@Override
	public void deleteEntity(String entityId) {
		entities.remove(entityId);
	}

	@Override
	public Entity getEntity(String entityId) {
		return entities.get(entityId);
	}
}

class OraclePersistenceImplementor implements PersistenceImplementor {
	private final Map<String, Entity> entities = new HashMap<>();
	
	@Override
	public void saveEntity(Entity entity) {
		entities.put(entity.getId(), entity);
	}

	@Override
	public void deleteEntity(String entityId) {
		entities.remove(entityId);
	}

	@Override
	public Entity getEntity(String entityId) {
		return entities.get(entityId);
	}
}