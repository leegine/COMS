head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3ProcessSleRecoveryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3ProcessSleRecoveryRequest�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/26 ��(FLJ) �V�K�쐬
 */
package webbroker3.slegateway.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * ���J�o���[���b�Z�[�W������
 * 
 * @@author      ���iFLJ�j
 * @@version     V1.0  
 */
public class WEB3ProcessSleRecoveryRequest extends WEB3GenRequest
{

    /** ���̃��b�Z�[�W��PTYPE�ł��B */
    public static final String PTYPE = "RECOVERY_REQUEST";
    
    /**
     * �X���b�hNO 
     */
    public  Long threadNo;
    
    /**
     * �����J�nID
     */
    public long fromAccountId;
    
    /**
     * �����I��ID
     */
    public long toAccountId;
    
    /**
     * ���N�G�X�g���M����
     */
    public Date date;

    /* (�� Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        // TODO �����������ꂽ���\�b�h�E�X�^�u
        return null;
    }

}
@
