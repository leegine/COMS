head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������̓��N�G�X�g�N���X(WEB3MutualSellInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ���� (���u) �V�K�쐬
                   2004/08/25 ���E (���u) ���r���[  
                   2004/12/07 ������ (���u) �c�Ή� 
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M�������̓��N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0   
 */

public class WEB3MutualSellInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408120915L;
    
    /**
     * ���M����ID
     */
    public String id;
    
    /**
     * �������@@<BR>
     * <BR>
     * null:�w�薳���@@0:��񐿋��@@1:���搿��<BR>
     */
    public String sellBuyDiv;
    
    /**
        * ���O���[�e�B���e�B<BR>
        */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellInputRequest.class);
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A8900F022A
     */
    public WEB3MutualSellInputRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�����̓��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A8901B01EC
     */
    public WEB3GenResponse createResponse() 
    {
        return  new WEB3MutualSellInputResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P)�@@ID�`�F�b�N<BR>
     * �@@ID��null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00080 <BR>
     * <BR>
     * �Q)�@@�������@@�`�F�b�N<BR>
     * �Q�|�P�jthis.�������@@==null�̏ꍇ�A��O���X���[����B <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00401 <BR>
     * �Q�|�Q�j�������@@���ȉ��̂�����ɂ����Ă͂܂�Ȃ��ꍇ�A��O���X���[����B<BR>
     *     �E�h�w�薳���h<BR>
     * �@@�@@�E�h��񐿋��h<BR>
     * �@@�@@�E�h���搿���h<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00402 <BR>
     * @@roseuid 40A8904B01CC
     */
    public void validate() throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P)�@@ID�`�F�b�N
        
        //ID��null�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("�h�c�����w��ł��B");
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00080,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�h�c�����w��ł��B"); 
        }

        //�Q�|�Q�j�������@@���ȉ��̂�����ɂ����Ă͂܂�Ȃ��ꍇ�A��O���X���[����B
         //�E�h�w�薳��.��񐿋��E���搿��
        if (!WEB3StringTypeUtility.isEmpty(this.sellBuyDiv) &&
            !WEB3ClaimDivDef.SELL.equals(this.sellBuyDiv) &&
            !WEB3ClaimDivDef.BUY.equals(this.sellBuyDiv))
        {
            log.debug("�������@@���h�w�薳���h�A�h��񐿋��h�A�h���搿���h�ȊO�̏ꍇ�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00402,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�������@@���h�w�薳���h�A�h��񐿋��h�A�h���搿���h�ȊO�̏ꍇ�B"); 
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
