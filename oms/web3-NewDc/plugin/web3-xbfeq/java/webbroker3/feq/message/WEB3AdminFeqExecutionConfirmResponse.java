head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.25.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������o�����͊m�F���X�|���X(WEB3AdminFeqExecutionConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҊO�������o�����͊m�F���X�|���X)<BR>
 * �Ǘ��ҊO�������o�����͊m�F���X�|���X�N���X
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executionConfirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;    
    
    /**
     * (�o���ڍ׏��ꗗ)<BR>
     * �O���������ڍׂ̔z��
     */
    public WEB3FeqExecDetailInfoUnit[] execDetailInfoList;
    
    /**
     * (���בփ��[�g)<BR>
     * ���בփ��[�g
     */
    public String execExchangeRate;
    
    /**
     * (����)<BR>
     * ����
     */
    public Date executionDate;
    
    /**
     * (���n��n��)<BR>
     * ���n��n��
     */
    public Date localDeliveryDate;
    
    /**
     * (���n�v)<BR>
     * ���n�v
     */
    public String passProfit;
    
    /**
     * (���n�v��)<BR>
     * ���n�v��
     */
    public String passProfitTax;
    
    /**
     * @@roseuid 42CE39FD037A
     */
    public WEB3AdminFeqExecutionConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqExecutionConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
