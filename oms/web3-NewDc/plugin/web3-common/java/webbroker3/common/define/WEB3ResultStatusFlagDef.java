head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ResultStatusFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����FLAG�i���ό��ʁj  �萔��`�C���^�t�F�C�X(WEB3ResultStatusFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23�@@�d�� (SRA) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * ����FLAG�i���ό��ʁj�@@�萔��`�C���^�t�F�C�X
 *
 * @@author �d��(SRA)
 * @@version 1.0
 */
public interface WEB3ResultStatusFlagDef
{

    /**
     *  �O�F�������@@
     */
    public static final String NOT_DEAL = "0";

    /**
     *  �P�F�ʒm��M�@@
     */
    public static final String NOTIFY_RECEIPT = "1";

    /**
     *  �Q�F�������M�@@
     */
    public static final String RESPONSE_SEND = "2";
    
    /**
     *  �R�F�ʒm�G���[�iFAIL�j
     */
    public static final String NOTIFY_ERROR_FAIL = "3";
    
    /**
     *  �S�F�ʒm�G���[�iERROR�j�@@
     */
    public static final String NOTIFY_ERROR_ERROR = "4";
    
    /**
     *  �T�F�����G���[�i�d���s���j
     */
    public static final String RESPONSE_ERROR_TELEGRAM = "5";
    
    /**
     *  �U�F�Z�b�V�����G���[�iCOMPLETE�j
     */
    public static final String SESSION_ERROR_COMPLETE = "6";
    
    /**
     *  �V�F�Z�b�V�����G���[�iCOMPLETE�ȊO�j
     */
    public static final String SESSION_ERROR_COMPLETE_EXCEPT = "7";
    
    /**
     *  �W�F�]�͌v�Z���s�@@
     */
    public static final String REMAINING_CALCULATION_FAIL = "8";
    
    /**
     *  �X�F�]�͍Čv�Z�����@@
     */
    public static final String REMAINING_CALCULATION_COMPLETE = "9";
    
    /**
     *  �`�F���ύď�������
     */
    public static final String SETTLEMENT_RESEND_PROCESS_COMPLETE= "A";

     
}
@
