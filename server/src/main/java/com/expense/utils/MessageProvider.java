package com.expense.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import com.expense.exception.MessageDoesNotExistException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MessageProvider {
	
	private final MessageSource ms;
	
	public String getMessage(String key) throws MessageDoesNotExistException {
		try {
			return ms.getMessage(key, null,null);
		}
		catch (NoSuchMessageException e) {
			throw new MessageDoesNotExistException(key+" is not exist in messages properties");
		}
		
	}
	
}
