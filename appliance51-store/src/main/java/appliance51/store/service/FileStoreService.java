package appliance51.store.service;

import appliance51.store.StoreConfig;
import appliance51.store.model.StoredFileInfo;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuananyun on 2016/10/16.
 */
@Service
public class FileStoreService {
    private static final Logger logger = LoggerFactory.getLogger(FileStoreService.class);

    @Autowired
    private StoreConfig storeConfig;


    public String upload(byte[] content, String fileName, String bucketName) {
        //创建上传对象
        UploadManager uploadManager = new UploadManager();
        try {
            //调用put方法上传
            Response res = uploadManager.put(content, fileName, storeConfig.getUploadToken(bucketName, null));
            if(res.statusCode==200)
            return getDownloadUrl(fileName);
            else return null;
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            logger.error(r.toString());
            try {
                //响应的文本信息
                logger.error(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
            return null;
        }
    }

    public String upload(String filePath, String fileName, String bucketName) {
        //创建上传对象
        UploadManager uploadManager = new UploadManager();
        try {
            Response res = uploadManager.put(filePath, fileName, storeConfig.getUploadToken(bucketName, null));
            if(res.statusCode==200)
                return getDownloadUrl(fileName);
            else return null;
        } catch (QiniuException e) {
            Response r = e.response;
            logger.error(r.toString());
            try {
                //响应的文本信息
                logger.error(r.bodyString());
            } catch (QiniuException e1) {
            }
            return null;
        }
    }


    /**
     * 获取文件的下载连接
     *
     * @param fileKey 文件的key或者文件名
     * @return
     */
    public String getDownloadUrl(String fileKey) {
        String downloadRUL = storeConfig.getAuth().privateDownloadUrl(storeConfig.getBucketDomain() + "/" + fileKey, 1728000);//20天
        return downloadRUL;
    }

    public StoredFileInfo getFileInfo(String fileKey) {
        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(storeConfig.getAuth());
        try {
            //调用stat()方法获取文件的信息
            FileInfo info = bucketManager.stat(storeConfig.getDefaultBucketName(), fileKey);
            StoredFileInfo storedFileInfo = new StoredFileInfo();
            storedFileInfo.setFsize(info.fsize);
            storedFileInfo.setHash(info.hash);
            storedFileInfo.setKey(info.key);
            storedFileInfo.setMimeType(info.mimeType);
            storedFileInfo.setPutTime(info.putTime);
            return storedFileInfo;
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            logger.error(r.toString());
            return null;
        }
    }

    public boolean deleteFile(String fileKey) {
        BucketManager bucketManager = new BucketManager(storeConfig.getAuth());
        try {
            //调用delete方法移动文件
            bucketManager.delete(storeConfig.getDefaultBucketName(), fileKey);
            return true;
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            logger.error(r.toString());
            return false;
        }
    }


}
