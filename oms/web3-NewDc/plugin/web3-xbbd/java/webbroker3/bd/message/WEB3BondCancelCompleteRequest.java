head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.53.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������N�G�X�g(WEB3BondCancelCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (������������N�G�X�g)<BR>
 * ������������N�G�X�g<BR>
 * 
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3BondCancelCompleteRequest extends WEB3GenRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondCancelCompleteRequest.class);
    
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_cancel_complete";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609201906L;
    
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String orderId;
    
    /**
     * (�m�F��������)<BR>
     * �m�F��������<BR>
     */
    public Date checkDate;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;
    
    /**
     * @@roseuid 44E3363D00DA
     */
    public WEB3BondCancelCompleteRequest()
    {
        
    }
    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>  
     * <BR>
     * �P�j����ID�`�F�b�N <BR>
     * �@@�@@����ID == null �̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00600<BR> 
     * <BR>
     * �Q�j�@@�m�F���������`�F�b�N <BR>
     * �@@�@@�@@�m�F�������� == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00078<BR> 
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j����ID�`�F�b�N 
        //����ID==null�̏ꍇ�A��O���X���[����B 
        if (this.orderId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }
        
        //�Q�j�m�F���������`�F�b�N 
        //�m�F�������� == null�̏ꍇ�A��O���X���[����B 
        if (this.checkDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�m�F�������������͂���Ă��܂���B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>  
     *<BR>  
     * @@return WEB3GenResponse  
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3BondCancelCompleteResponse(this);
    }

}
@
