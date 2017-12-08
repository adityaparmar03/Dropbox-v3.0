package in.adityaparmar.server.service;

import in.adityaparmar.server.entity.Content;
import in.adityaparmar.server.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public List<Content> getFolderData(){

        return contentRepository.findAllByUserid(1);
    }
}
