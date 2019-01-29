head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceDetailsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃��N�G�X�g(WEB3AdminSrvRegiServiceDetailsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 �s�p (���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃��N�G�X�g)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃��N�G�X�g�N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceDetailsRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceDetails";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceDetailsRequest.class);
    
    /**
     * (ID)<BR>
     * �T�[�r�X�敪<BR>
     */
    public String serviceDiv;
    
    /**
     * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃��N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE4E48013F
     */
    public WEB3AdminSrvRegiServiceDetailsRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ڍ׃��X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40EE4E48015E
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiServiceDetailsResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) ID�̃`�F�b�N<BR>
     *  1-1) this.ID==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     *  1-2) this.ID�̌������A2���܂���4���ȊO�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00954<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40EE4E48016D
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //1) ID�̃`�F�b�N
        //1-1) this.ID==null�̏ꍇ�A��O���X���[����B
        if (this.serviceDiv == null || "".equals(this.serviceDiv.trim()))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1-2) this.ID�̌������A2���܂���4���ȊO�̏ꍇ�A��O���X���[����B
        if (this.serviceDiv.length() != 2 && this.serviceDiv.length() != 4)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00954, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.entering(STR_METHOD_NAME);
    }
}
@
