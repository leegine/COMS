head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.31.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptCancelUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������t������(WEB3FeqOrderAcceptCancelUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�O������������t������)<BR>
 * �O������������t������׃N���X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptCancelUnit extends Message 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptCancelUnit.class);
        
    /**
     * (����ID)<BR>
     * ����ID
     */
    public String orderId;
    
    /**
     * (�ύX���t�敪)<BR>
     * �ύX���t�敪<BR>
     * <BR>
     * 01�F������t��<BR>
     * 02�F������t�G���[<BR>
     * 03�F������t�ώ��<BR>
     * <BR>
     * 11�F������<BR>
     * 12�F�����G���[<BR>
     * <BR>
     * 21�F�����<BR>
     * 22�F����G���[<BR>
     * <BR>
     * 31�F�o����<BR>
     */
    public String aftChangeAcceptDiv;
    
    /**
     * (�O������������t������)<BR>
     * �R���X�g���N�^
     * @@roseuid 4201F2E0017C
     */
    public WEB3FeqOrderAcceptCancelUnit() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�����h�c�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * �Q�j�@@�ύX��敪�̃`�F�b�N<BR>
     * �@@�����͂�����ꍇ�̂�<BR>
     * �@@�Q�|�P�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02020<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A55527015E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����h�c�̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + STR_METHOD_NAME,
                " ����ID�����w��ł��B"); 
        }
        
        //�Q�j�@@�ύX��敪�̃`�F�b�N
        //�����͂�����ꍇ�̂�
        if (!WEB3StringTypeUtility.isEmpty(this.aftChangeAcceptDiv))
        {
            //�Q�|�P�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B
            if (!(WEB3FeqAcceptTypeDef.CANCEL.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CANCEL_ERROR.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CHANGE_ERROR.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CHANGED.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(this.aftChangeAcceptDiv) || 
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR.equals(this.aftChangeAcceptDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02020,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ���͂��ꂽ�ύX��敪���s���ȃR�[�h�l�ł�:" + this.aftChangeAcceptDiv); 
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
