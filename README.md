# spring_library
Spring + Hibernate + Maven

### openSession
When you call SessionFactory.openSession, it always create new Session object afresh and give it to you. You need to explicitly flush and close these session objects. As session objects are not thread safe, you need to create one session object per request in multithreaded environment and one session per request in web applications too.
### getCurrentSession
When you call SessionFactory. getCurrentSession, it will provide you session object which is in hibernate context and managed by hibernate internally. It is bound to transaction scope.
When you call SessionFactory. getCurrentSession , it creates a new Session if not exists , else use same session which is in current hibernate context. It automatically flush and close session when transaction ends, so you do not need to do externally.
If you are using hibernate in single threaded environment , you can use getCurrentSession, as it is faster in performance as compare to creating  new session each time.
