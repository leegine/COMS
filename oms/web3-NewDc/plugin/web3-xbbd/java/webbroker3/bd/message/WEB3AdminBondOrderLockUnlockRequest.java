head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.37.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderLockUnlockRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ��������b�N�敪�X�V���N�G�X�g(WEB3AdminBondOrderLockUnlockRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.define.WEB3BondOrderLockDivDef;


/**
 * (�Ǘ��ҍ��������b�N�敪�X�V���N�G�X�g)<BR>
 * �Ǘ��ҍ��������b�N�敪�X�V���N�G�X�g�N���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondOrderLockUnlockRequest extends WEB3GenRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderLockUnlockRequest.class);
    
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_order_lock_unlock";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (�������b�N�敪)<BR>
     * �������b�N�敪<BR>
     * 1�F���b�N�����@@2�F���b�N
     */
    public String orderLockDiv;
    
    /**
     * (����ID)<BR>
     * ����ID
     */
    public String id;
    
    /**
     * @@roseuid 44E336390242
     */
    public WEB3AdminBondOrderLockUnlockRequest() 
    {
     
    }   
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR> 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �������b�N�敪<BR>
     * �E���b�N����<BR>
     * �E���b�N<BR>
     * ��L�ȊO�̏ꍇ�́A�G���[���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02580<BR>
     * <BR>
     * ����ID�F<BR>
     * �Enull�̏ꍇ�́A�G���[���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00600
     * @@throws WEB3BaseException 
     * @@roseuid 44C426C10155
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�������b�N�敪
        //�E���b�N����
        //�E���b�N
        //��L�ȊO�̏ꍇ�́A�G���[���X���[����B
        //class:�@@WEB3BusinessLayerException
        //tag:�@@�@@BUSINESS_ERROR_02580
        if ((!WEB3BondOrderLockDivDef.LOCK_RELEAS.equals(this.orderLockDiv)) && 
                (!WEB3BondOrderLockDivDef.LOCK.equals(this.orderLockDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02580,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������b�N�敪���h���b�N�����h�A�h���b�N�h�ȊO�̒l�ł��B");
        }
        
        //����ID�F
        //�Enull�̏ꍇ�́A�G���[���X���[����B
        //class:�@@WEB3BusinessLayerException
        //tag:�@@�@@BUSINESS_ERROR_00600
        if (this.id == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * �Ǘ��Ғ������b�N�敪�X�V���X�|���X�𐶐����Ԃ�
     * @@return WEB3GenResponse
     * @@roseuid 44C426B700A9
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminBondOrderLockUnlockResponse(this);
    }
}
@
