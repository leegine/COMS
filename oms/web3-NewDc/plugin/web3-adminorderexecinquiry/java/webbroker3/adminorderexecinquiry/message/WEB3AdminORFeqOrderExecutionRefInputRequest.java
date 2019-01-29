head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqOrderExecutionRefInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�O�������������Ɖ���̓��N�G�X�g(WEB3AdminORFeqOrderExecutionRefInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�O�������������Ɖ���̓��N�G�X�g)<BR>
 * �Ǘ��ҁE�O�������������Ɖ���̓��N�G�X�g�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminORFeqOrderExecutionRefInputRequest extends WEB3GenRequest
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminORFeqOrderExecutionRefInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_Feq_ORFeqOrderExecutionRefInput";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507130937L;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��<BR>
     * <BR>
     * �����X�R�[�h�����͎��́APR�w�ŕێ����Ă���<BR>
     * �@@�戵�\���X�R�[�h�ꗗ���Z�b�g�����B<BR>
     */
    public String[] branchCode;
    
    /**
     * @@roseuid 42D1C8E90399
     */
    public WEB3AdminORFeqOrderExecutionRefInputRequest() 
    {
        
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �Ǘ��ҁE�O�������������Ɖ���̓��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF7B460371
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminORFeqOrderExecutionRefInputResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���X�R�[�h�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���X�R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02174<BR>
     * <BR>
     * �@@�P�|�Q�jthis.���X�R�[�h.length == 0�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h�̗v�f����0�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02175<BR>
     * @@throws �V�X�e�����ʁiweb3-common�j.(web3)�V�X�e�������N���X_common.WEB3BaseException
     * @@throws WEB3BaseException
     * @@roseuid 42A65BDE0291
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���X�R�[�h�`�F�b�N
        //�@@�P�|�P�jthis.���X�R�[�h == null�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B
        if (this.branchCode == null)
        {
            String l_strMessage = "���X�R�[�h��null�ł��B";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //
        //�@@�P�|�Q�jthis.���X�R�[�h.length == 0�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u���X�R�[�h�̗v�f����0�v�̗�O���X���[����B
        if (this.branchCode.length == 0)
        {
            String l_strMessage = "���X�R�[�h�̗v�f����0�ł��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02175,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
