head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.59.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleMarketAdapterErrorMessageDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3SleMarketAdapterErrorMessageDefine�N���X
Author Name      : Daiwa Institute of Research
Revision History : 2006/10/23 �� �V�K�쐬
*/

package webbroker3.slegateway.define;

/**
 * �s�꒼��SLE�G���[���b�Z�[�W ��`�C���^�t�F�C�X
 */
public class WEB3SleMarketAdapterErrorMessageDef {
	
	/**
	 * �s���t�\�Ȏ��ԑъO�̂��߁A���M���s�ł����B
	 */
	public static final String MARKET_STATUS_NOVALID_PERIOD = "The market is already closed for the day.";
	
	/** 
	 * �s�ꂪ���M�ł��Ȃ��X�e�[�^�X�̂��߁A���M���܂���ł����B 
	 */
	public static final String MARKET_STATUS_NOVALID_GLLD = "the order sending failed by the invalid market status.";

	/** 
	 * SLE���M�R�l�N�^�̓I�t���C���܂��̓N���[�Y��Ԃł��邽�߁A���M���s�ł����B
	 */
	public static final String SLE_CONNECTOR_OFFLINE_STATUS = "the order sending failed because SLE Connector is at offline.";

	/** 
	 * �����X���b�h��p���b�N�擾�ł��Ȃ����ߏ������~�B 
	 */
	public static final String SEND_THREAD_LOCKED_STATUS = "The Thread belonging the same no is locked.";

	/** 
	 * �������N�G�X�g���d�����đ��M���Ȃ����߁A���M���܂���ł����B 
	 */
	public static final String ORDER_MESSAGE_SEND_DUPLI = "the order request will be send duplicately.";
	
	/**
	 * �s��ɑΉ�����SLE�R�l�N�^��GLID�����݂��Ȃ����ߑ��M���܂���ł����B
	 */
	public static final String MARKET_GLID_NOT_EXISTED = "sle GLID for specified Market is not existed .";
    
	/** 
	 * SLE�R�l�N�^����~�܂��͐ڑ������̂��ߑ��M���s�ł����B 
	 */
	public static final String SLE_ADAPTER_STOP = "the order sending failed because the sle connector is closed or link lost.";   

	/** 
	 * SLE��ORDER_BOOK�ւ̖₢���킹�����s�ł����B
	 */
	public static final String SLE_ORDER_BOOK_REQUEST_FAIL = "the query request for sle order book failed.";
    
	/**
	 * ���J�o���[�s�\��Ԃł��邽�ߏ������s�ł����B 
	 */
	public static final String SLE_RECOVERY_NOT_AVAILABLE = "recovery operation stopped because SLE connection is not available.";
}


@
