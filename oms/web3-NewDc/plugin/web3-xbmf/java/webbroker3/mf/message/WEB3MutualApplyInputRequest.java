head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualApplyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M��W�������̓��N�G�X�g�N���X(WEB3MutualApplyInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 ���� (���u) �V�K�쐬
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M��W�������̓��N�G�X�g�N���X <BR>
 * 
 * @@author ����(���u)
 * @@version 1.0   
 */

public class WEB3MutualApplyInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_apply_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200509261532L;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualApplyInputRequest.class);
    
    /**
     * (���M����ID)<BR>  
     *  ���M����ID<BR>  
     */
    public String id;
    
    /**
     * (�d�q���`�F�b�N�t���O)<BR>  
     *  �d�q���`�F�b�N�t���O<BR>  
     *  true�F�`�F�b�N�v  false�F�`�F�b�N�s�v <BR>     
     */
    public boolean batoCheckFlag;
    
    /**
     * (��ʃR�[�h)<BR>  
     *  ��ʃR�[�h<BR>  
     */
    public String typeCode;
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>  
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>  
     * <BR>
     * �P)�@@����ID�`�F�b�N <BR>
     * this.ID==null�̏ꍇ�A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02229 <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C656B50132
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P)�@@����ID�`�F�b�N
        //this.ID==null�̏ꍇ�A��O���X���[����B
        if (this.id == null)
        {
            log.debug("����ID�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");       
        }   
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * ���M��W�������̓��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B5029E
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MutualApplyInputResponse(this);
    }   
}
@
