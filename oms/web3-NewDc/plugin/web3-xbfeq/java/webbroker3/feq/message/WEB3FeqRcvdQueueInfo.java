head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.31.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqRcvdQueueInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������RCVD�L���[���(WEB3FeqRcvdQueueInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 �����q (���u) �V�K�쐬
                   2007/01/15 ����� (���u) ���f�� No.331
Revesion History : 2008/10/02 ����(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.465
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;



/**
 * (�O������RCVD�L���[���)<BR>
 * �O������RCVD�L���[���N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3FeqRcvdQueueInfo extends Message
{
    /**
     * (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     */
    public String updaterCode;
    
    /**
     * (�^�p�R�[�h)<BR>
     * �^�p�R�[�h
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
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 1�F���t<BR>
     * 2�F���t<BR>
     */
    public String dealingType;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
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
     * (������)<BR>
     * ������<BR>
     */
    public Date executionTimestamp;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public Date executionDate;
    
    /**
     * (���n��n��)<BR>
     * ���n��n��<BR>
     */
    public Date localDeliveryDate;
    
    /**
     * (�בփ��[�g)<BR>
     * �בփ��[�g<BR>
     */
    public String exchangeRate;
    
    /**
     * (���ʔ�)<BR>
     * ���ʔ�<BR>
     */
    public String executeNo;
    
    /**
     * (�o�H�敪 )<BR>
     * �o�H�敪 <BR>
     * <BR>
     * 0�F�o���ʒm<BR> 
     * 1�F�o������<BR>
     * 2�F��茋�ʈꊇ����<BR>
     * 3�F������t<BR>
     * 4�F������t����F��<BR>
     * 5�F������t���ʈꊇ����<BR>
     */
    public String orderRootDiv;
    
    /**
     * (��t�敪)<BR>
     * ��t�敪 <BR>
     * <BR>
     * 01�F������t��<BR>
     * 09�F������t�G���[<BR>
     * 03�F������t�ώ��<BR>
     * 11�F������<BR>    
     * 19�F�����G���[<BR>  
     * 21�F�����<BR>
     * 29�F����G���[<BR>
     * 31�F�o���� <BR>
     */
    public String acceptDiv;
    
    /**
     * (���P��)<BR>
     * ���P��<BR>
     */
    public String execPrice;
    
    /**
     * (��萔��)<BR>
     * ��萔��<BR>
     */
    public String execQuantity;
    
    /**
     * (�����敪)<BR>
     * �����敪  <BR>
     * <BR>
     * 0�F�����҂�<BR>
     * 1�F�����ς�<BR> 
     * 7�F��菈����<BR>    
     * 8�F�����ȗ�<BR>     
     * 9�F�����G���[<BR>
     */
    public String transactionDiv;

    /**
     * (���ی����R�[�h)<BR>
     * ���ی����R�[�h<BR>
     */
    public String rejectCauseCode;

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
     * (�O������RCVD�L���[���)<BR>
     * �R���X�g���N�^<BR>
     */ 
    public WEB3FeqRcvdQueueInfo()
    {
        
    }
}
@
