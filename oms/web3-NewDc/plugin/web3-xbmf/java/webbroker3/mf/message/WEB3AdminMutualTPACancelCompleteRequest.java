head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPACancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���Ǘ��җ]�͒�������������N�G�X�g(WEB3AdminMutualTPACancelCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 ���� (���u) �V�K�쐬
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 *(�����M���Ǘ��җ]�͒�������������N�G�X�g)<BR>
 *�����M���Ǘ��җ]�͒�������������N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0   
 */

public class WEB3AdminMutualTPACancelCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_tpa_cancel_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512191632L;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualTPACancelCompleteRequest.class);
    
    /**
     * (�ڋq�R�[�h)<BR>
     *  �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (�����h�c)<BR>
     *  �����h�c<BR>
     */
    public String orderId;
    
    /**
     * (�Ïؔԍ�)<BR>
     *  �Ïؔԍ�<BR>
     */
    public String password;
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>  
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>  
     * <BR>
     *�P�j�ڋq�R�[�h�`�F�b�N  <BR>
     *    this.�ڋq�R�[�h==null�̏ꍇ�A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00835 <BR>
     * <BR>
     *�Q�j����ID�`�F�b�N  
     *    this.����ID==null�̏ꍇ�A��O���X���[����B<BR>
     *<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00600 <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C656B50132
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�ڋq�R�[�h�`�F�b�N
        //this.�ڋq�R�[�h==null�̏ꍇ�A��O���X���[����B
        if (this.accountCode == null)
        {
            log.debug("�ڋq�R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�����w��ł��B");            
        }
        
        //�Q�j����ID�`�F�b�N 
        //this.����ID==null�̏ꍇ�A��O���X���[����B
        if (this.orderId == null)
        {
            log.debug("����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");   
        }
        log.exiting(STR_METHOD_NAME);	
    }
    
    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * ���M��W�����������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B5029E
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualTPACancelCompleteResponse(this);
    } 

}
@