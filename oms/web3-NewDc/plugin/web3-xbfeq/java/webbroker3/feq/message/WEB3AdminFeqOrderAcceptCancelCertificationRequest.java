head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptCancelCertificationRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������������t����F�؋��ʃ��N�G�X�g(WEB3AdminFeqOrderAcceptCancelCertificationRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҊO������������t����F�؋��ʃ��N�G�X�g)<BR>
 * �Ǘ��ҊO������������t����F�؋��ʃ��N�G�X�g�N���X
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptCancelCertificationRequest extends WEB3GenRequest 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptCancelCertificationRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAcceptCancelCertification";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (������t����ꗗ)<BR>
     * �O������������t������̔z��
     */
    public WEB3FeqOrderAcceptCancelUnit[] orderAcceptCancelList;
    
    /**
     * @@roseuid 42CE39FC007D
     */
    public WEB3AdminFeqOrderAcceptCancelCertificationRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̃`�F�b�N���s��<BR>
     * <BR>
     * �P�j�@@������t����ꗗ[]�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02043<BR>
     * �@@�P�|�Q�j�@@�z��̊e�v�f���ɁA������t�����.validate()���R�[������B
     * @@throws WEB3BaseException
     * @@roseuid 42A555D5021A
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@������t����ꗗ[]�̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.orderAcceptCancelList == null || this.orderAcceptCancelList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02043,
                this.getClass().getName() + STR_METHOD_NAME,
                " ������t����ꗗ�������͂ł��B");         
        }

        //�P�|�Q�j�@@�z��̊e�v�f���ɁA������t�����.validate()���R�[������B
        int l_intCnt = this.orderAcceptCancelList.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3FeqOrderAcceptCancelUnit l_unit = orderAcceptCancelList[i];
            if (l_unit != null)
            {
                l_unit.validate();
            }
        }
        
        log.exiting(STR_METHOD_NAME); 
     
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
