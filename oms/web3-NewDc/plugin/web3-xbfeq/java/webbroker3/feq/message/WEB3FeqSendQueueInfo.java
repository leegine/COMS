head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSendQueueInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������SEND�L���[���(WEB3FeqSendQueueInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 �����q (���u) �V�K�쐬
Revesion History : 2006/12/11 ꎉ�   (���u) ���f�� No.310
Revesion History : 2007/01/15 ����� (���u) ���f�� No.331
Revesion History : 2007/02/02 ������ (���u) ���f�� No.341
Revesion History : 2008/02/01 �g�C�� (���u) ���f�� No.395
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�O������SEND�L���[���)<BR>
 * �O������SEND�L���[���N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3FeqSendQueueInfo extends Message
{
    /**
     * (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     */
    public String managementCode;

    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     */
    public String requestNumber;

    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String orderId;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String orderBizDate;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (�I�y���[�^�^�C�v)<BR>
     * �I�y���[�^�^�C�v<BR>
     * <BR>
     * 0�F�V�K<BR>
     * 1�F���<BR>
     * 2�F����<BR>
     */
    public String operatorType;
    
    /**
     * (�������)<BR>
     * �������<BR>
     * <BR>
     * 701�F���t<BR>
     * 702�F���t<BR>
     */
    public String orderType;
    
    /**
     * (�w�l)<BR>
     * �w�l<BR>
     */
    public String orderPrice;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public String orderQuantity;
    
    /**
     * (�����O�w�l)<BR>
     * �����O�w�l<BR>
     */
    public String beforeOrderPrice;
    
    /**
     * (�����O����)<BR>
     * �����O����<BR>
     */
    public String beforeOrderQuantity;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�����҂�<BR> 
     * 1�F�����ς�<BR>
     * 2�F�đ��M�҂�<BR>
     * 6�F���M�������<BR>
     * 7�F�����M<BR>
     * 8�F�����ȗ�<BR>
     * 9�F�����G���[<BR>
     */
    public String transactionDiv;
    
    /**
     * (���[�����M����)<BR>
     * ���[�����M����<BR>
     */
    public Date sendMailDate;
    
    /**
     * (�쐬���t)<BR>
     * �쐬���t<BR>
     */
    public Date createTimeStamp;
    
    /**
     * (�X�V���t)<BR>
     * �X�V���t<BR>
     */
    public Date updateTimeStamp;
    
    /**
     * (�L���[ID)<BR>
     * �L���[ID<BR>
     */
    public String queueId;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;
    
    /**
     * (�O������SEND�L���[���)<BR>
     * �R���X�g���N�^<BR>
     */  
    public WEB3FeqSendQueueInfo()
    {
        
    }
}
@
