head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.33.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������o�����̓��X�|���X(WEB3AdminFeqExecutionInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҊO�������o�����̓��X�|���X)<BR>
 * �Ǘ��ҊO�������o�����̓��X�|���X�N���X
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executionInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h�i�����ԍ��j
     */
    public String requestNumber;
    
    /**
     * (����ID)<BR>
     * ����ID
     */
    public String orderId;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (��������敪)<BR>
     * ��������敪<BR>
     * <BR>
     * 0�F���<BR>
     * 1�F����
     */
    public String taxType;
    
    /**
     * (�`�[�ԍ�)<BR>
     * �`�[�ԍ�
     */
    public String orderNumber;
    
    /**
     * (��������)<BR>
     * ��������
     */
    public Date orderDate;
    
    /**
     * (������)<BR>
     * ������
     */
    public Date orderBizDate;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * (���n�����R�[�h)<BR>
     * ���n�����R�[�h
     */
    public String localProductCode;
    
    /**
     * (������)<BR>
     * ������
     */
    public String productName;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 1�F���t<BR>
     * 2�F���t
     */
    public String dealingType;
    
    /**
     * (��������)<BR>
     * ��������
     */
    public String orderQuantity;
    
    /**
     * (�����P��)<BR>
     * �����P��
     */
    public String orderPrice;
    
    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * <BR>
     * 0�F�~�݌���<BR>
     * 1�F�O�݌���
     */
    public String settleDiv;
    
    /**
     * (���בփ��[�g)<BR>
     * ���בփ��[�g
     */
    public String execExchangeRate;
    
    /**
     * @@roseuid 42CE39FE007D
     */
    public WEB3AdminFeqExecutionInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqExecutionInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
