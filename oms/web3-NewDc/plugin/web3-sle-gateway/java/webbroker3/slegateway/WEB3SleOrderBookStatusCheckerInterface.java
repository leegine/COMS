head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleOrderBookStatusCheckerInterface.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SleOrderBookStatusChecker�N���X
Author Name      : Daiwa Institute of Research
Revision History : 2006/06/06 ���iFLJ) �V�K�쐬
*/
package webbroker3.slegateway;

import java.util.Map;
import webbroker3.slebase.data.SleSendQRow;

/**
 * SLE��ORDER_BOOK�₢���킹�c�[���N���X
 * @@author  : ���iFLJ�j
 * @@version : 1.0
 */
public interface WEB3SleOrderBookStatusCheckerInterface {

	/**
	 * SEND_Q�������b�Z�[�W������SLE�ɐ��������M����邩�`�F�b�N
	 * @@param sleSendqRow SEND_Q�������b�Z�[�W
	 * @@return ���M�ς�:true��Ԃ��@@��:false��Ԃ��B
	 */ 
	public boolean isAlreadySent(SleSendQRow sleSendqRow) throws RuntimeException;
	
	/**
	 * SEND_Q�������b�Z�[�W������SLE�ɐ��������M����邩�`�F�b�N
	 * @@param sleSendqRow SEND_Q�������b�Z�[�W
	 * @@param orderBook �擾��������ORDER_BOOK
	 * @@return ���M�ς�:true��Ԃ��@@��:false��Ԃ��B
	 */
	public boolean isAlreadySent(SleSendQRow sleSendqRow, Map orderBook) throws RuntimeException;
	

	/**
	 * �s��R�[�h�A�����R�[�h�ɑΉ�����ORDER BOOK�₢���킹���ʎ擾
	 * @@param marketCode �s��R�[�h
	 * @@param productCode�@@�����R�[�h
	 * @@return�@@ORDER BOOK �̖₢���킹���ʂ�InternalRef (����ID)�̃}�b�s���O�Ή��֌W��ێ�����Map
	 */
	public Map getOrderBook(String marketCode, String productCode) throws RuntimeException;
}
@
