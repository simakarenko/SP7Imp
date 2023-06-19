package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final GroupRepository groupRepository;
    private final PicturesRepository picturesRepository;

    public ContactService(ContactRepository contactRepository, GroupRepository groupRepository, PicturesRepository picturesRepository) {
        this.contactRepository = contactRepository;
        this.groupRepository = groupRepository;
        this.picturesRepository = picturesRepository;
    }

    @Transactional
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Transactional
    public void addPicture(Picture picture) {
        picturesRepository.save(picture);
    }

    @Transactional
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public void deleteContacts(long[] idList) {
        for (long id : idList)
            contactRepository.deleteById(id);
    }

    @Transactional
    public void deletePictures(long[] idList) {
        for (long id : idList)
            picturesRepository.deleteById(id);
    }

    @Transactional
    public void deleteGroup(long id) {
        List<Group> groupList = groupRepository.findByIdGroupTest(id, null);
        Group gr = groupList.get(0);
        List<Contact> list = groupRepository.findByGroupTest(gr, null);
        for (Contact c : list)
            contactRepository.deleteById(c.getId());
    }

    @Transactional(readOnly = true)
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Contact> findAll(Pageable pageable) {
        return contactRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public List<Picture> findAllPictures(Pageable pageable) {
        return picturesRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public List<Contact> findByGroup(Group group, Pageable pageable) {
        return contactRepository.findByGroup(group, pageable);
    }

    @Transactional(readOnly = true)
    public long countByGroup(Group group) {
        return contactRepository.countByGroup(group);
    }

    @Transactional(readOnly = true)
    public List<Contact> findByPattern(String pattern, Pageable pageable) {
        return contactRepository.findByPattern(pattern, pageable);
    }

    @Transactional(readOnly = true)
    public long count() {
        return contactRepository.count();
    }

    @Transactional(readOnly = true)
    public Group findGroup(long id) {
        return groupRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public Picture findPicture(long id) {
        return picturesRepository.findById(id).get();
    }

    @Transactional
    public void reset() {
        groupRepository.deleteAll();
        contactRepository.deleteAll();

        Group group = new Group("Test");
        Contact contact;

        addGroup(group);

        for (int i = 0; i < 13; i++) {
            contact = new Contact(null, "Name" + i, "Surname" + i, "1234567" + i, "user" + i + "@test.com");
            addContact(contact);
        }
        for (int i = 0; i < 10; i++) {
            contact = new Contact(group, "Other" + i, "OtherSurname" + i, "7654321" + i, "user" + i + "@other.com");
            addContact(contact);
        }
        Group groupOne = new Group("TestOne");

        addGroup(groupOne);

        for (int i = 13; i < 23; i++) {
            contact = new Contact(groupOne, "Name" + i, "Surname" + i, "1234567" + i, "user" + i + "@testOne.com");
            addContact(contact);
        }
        Group groupTwo = new Group("TestTwo");

        addGroup(groupTwo);

        for (int i = 23; i < 33; i++) {
            contact = new Contact(groupTwo, "Name" + i, "Surname" + i, "1234567" + i, "user" + i + "@testTwo.com");
            addContact(contact);
        }
        Picture picture;
        File picturesCatalog = new File("/Users/svetlana/Documents/JavaPro Progects/SP7Imp/src/main/webapp/WEB-INF/static");
        File[] picturesFileList = picturesCatalog.listFiles();
        for (File f : picturesFileList) {
            picture = new Picture(f.getName());
            addPicture(picture);
        }
    }
}
