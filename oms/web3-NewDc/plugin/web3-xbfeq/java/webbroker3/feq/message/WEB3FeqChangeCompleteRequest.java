head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������������N�G�X�g(WEB3FeqChangeCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�����������������N�G�X�g)<BR>
 * �O�����������������N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqChangeCompleteRequest extends WEB3FeqChangeCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_changeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqChangeCompleteRequest.class);
    
    /**
     * (�m�F���P��)<BR>
     * �m�F���P��<BR>
     */
    public String checkPrice;
    
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
     * @@roseuid 42CE3A06034B
     */
    public WEB3FeqChangeCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j�m�F���P���`�F�b�N<BR>
     * <BR>
     *    this.�m�F���P�� == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00206<BR>
     * <BR>
     * �R�j�m�F���������`�F�b�N<BR>
     * <BR>
     *    this.�m�F�������� == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00078<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 4296021B006C
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�X�[�p�[�N���X��validate()���\�b�h���R�[������B
        super.validate();
        
        //�Q�j�m�F���P���`�F�b�N
        //this.�m�F���P�� == null�̏ꍇ�A��O���X���[����B
        if (this.checkPrice == null)
        {
            log.debug("�m�F���P�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206,
                this.getClass().getName() + STR_METHOD_NAME,
                "�m�F���P�������w��ł��B" + this.checkPrice);
        }

        //�R�j�m�F���������`�F�b�N
        //this.�m�F�������� == null�̏ꍇ�A��O���X���[����B
        if (this.checkDate == null)
        {
            log.debug("�m�F�������������͂���Ă��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + STR_METHOD_NAME,
                "�m�F�������������͂���Ă��܂���B" + this.checkDate);
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
        return new WEB3FeqChangeCompleteResponse(this);
    }
}
@
