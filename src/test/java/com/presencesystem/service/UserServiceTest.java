package com.presencesystem.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.collections4.IterableUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.presencesystem.dao.domain.User;
import com.presencesystem.dao.repository.UserRepository;
import com.presencesystem.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    UserService service;

    private UserRepository userRepository;

    @Before
    public void prepareEnvironment() {
        userRepository = mock(UserRepository.class);
        service = new UserServiceImpl(userRepository);
    }

    @Test
    public void testGetAllUser() {
        List<User> founds = new ArrayList<>();
        User user = new User();
        user.setId(2L);
        user.setFingerprint("123456789");

        founds.add(user);

        when(userRepository.findAll()).thenReturn(founds);

        Iterable<User> resultData = service.findAll();
        assertThat(IterableUtils.size(resultData), is(1));
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(2L);
        user.setFingerprint("123456789");
        user.setFirstName("Name");
        user.setLastName("lastName");
        user.setEmail("mail");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User input = new User();
        input.setFingerprint("123456789");
        input.setFirstName("Name");
        input.setLastName("lastName");
        input.setEmail("mail");

        User resultData = service.save(input);
        assertThat(resultData.getEmail(), is(input.getEmail()));
        assertThat(resultData.getId(), is(2L));
    }

    @Test
    public void testGetUser() {
        User user = new User();
        user.setId(2L);
        user.setFingerprint("123456789");
        user.setFirstName("Name");
        user.setLastName("lastName");
        user.setEmail("mail");

        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));

        User resultData = service.findOne(1L);
        assertThat(resultData.getId(), is(2L));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetUserWithException() {
        User user = new User();
        user.setId(2L);
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        service.findOne(1L);
    }

    @Test
    public void testGetUserByFingerprint() {
        User user = new User();
        user.setId(2L);
        user.setFingerprint("123456789");
        user.setFirstName("Name");
        user.setLastName("lastName");
        user.setEmail("mail");

        when(userRepository.findByFingerprint(any(String.class))).thenReturn(user);

        User resultData = service.findByFingerprint("123456789");
        assertThat(resultData.getId(), is(2L));
    }

    @Test
    public void testDeleteUserDashboard() {
        User user = new User();
        user.setId(2L);
        user.setFingerprint("123456789");

        service.delete(user);
        verify(userRepository).delete(any(User.class));
    }

}
