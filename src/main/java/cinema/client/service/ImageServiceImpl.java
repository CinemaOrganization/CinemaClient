package cinema.client.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final static String noImageDefaultId = "-1";
    private final static String serverPath = "/server/images";

    @Override
    public String saveImage(File image) {
//        if (image.getName().isEmpty()) {
            return noImageDefaultId;
//        }
//        String id = createId();
//        return id;
    }

    private String createId() {
        String id;
        //do {
            id = UUID.randomUUID().toString();
        //} while (!checkUniqueness(id));
        return id;
    }

    private boolean checkUniqueness(String uniqueID) {
        File folder = new File("serverPath");
        File[] listOfFiles = folder.listFiles();
        boolean checkedNameNotExist = Arrays.stream(listOfFiles)
                .filter(File::isFile)
                .map(File::getName)
                .noneMatch((o) -> o.equals(uniqueID));
        return checkedNameNotExist;
    }
}
