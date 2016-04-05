package cinema.client.SetupData;

import cinema.client.data.SessionRepository;

/**
 * Временный класс, для добавления/удаления временных данных в/из БД
 */
public class Datas {

    private SessionRepository repository;

    public Datas(SessionRepository repository) {
        this.repository = repository;
    }

    public void setUp() throws Exception {

        repository.save(SessionSetupData.setupData());
    }

    public void tearDown() {
        repository.deleteAll();
    }
}
