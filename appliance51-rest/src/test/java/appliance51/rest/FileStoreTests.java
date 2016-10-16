package appliance51.rest;

import appliance51.store.model.StoredFileInfo;
import appliance51.store.service.FileStoreService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yuananyun on 2016/10/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileStoreTests {

    @Autowired
    private FileStoreService fileStoreService;

    @Test
    public void 测试文件操作(){
        String fileKey = "2016/test1";
        fileStoreService.deleteFile(fileKey);
        fileStoreService.upload("d:\\test1.png", "2016/test1", null);
        String url = fileStoreService.getDownloadUrl("2016/test1");
        StoredFileInfo fileInfo = fileStoreService.getFileInfo("2016/test1");

    }


}
