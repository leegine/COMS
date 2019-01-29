head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p�����������_�V�K���������N�G�X�g�N���X(WEB3MarginOpenMarginChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�����������_�V�K���������N�G�X�g�j�B<br>
 * <br>
 * �M�p�����������_�V�K���������N�G�X�g�N���X
 * @@version 1.0
 */
public class WEB3MarginOpenMarginChangeCompleteRequest extends WEB3MarginCommonRequest 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginCancelCompleteRequest.class);

    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginChangeComplete";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;    
    
    /**
     * (����ID)
     */
    public String id;
    
    /**
     * (�m�F���P��)<BR>
     * <BR>
     * �m�F���X�|���X�ő��M�����l�B
     */
    public String checkPrice;
    
    /**
     * (�m�F��������)<BR>
     * <BR>
     * �m�F���X�|���X�ő��M�����l�B
     */
    public Date checkDate;
    
    /**
     * (�Ïؔԍ�)
     */
    public String password;
    
    /**
     * @@roseuid 41404539021D
     */
    public WEB3MarginOpenMarginChangeCompleteRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B<BR>
     * <BR>
     * �Q�j�@@�h�c�`�F�b�N<BR>
     * �@@this.�h�c��null�ł������ꍇ�A�uID��null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * �R�j�@@���������`�F�b�N<BR>
     * �@@�R�|�P�jthis.����������null�ł���΁A�u�������������w��v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40866CE90250
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        // �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B<BR>
        //
        super.validate();
        // �Q�j�@@�h�c�`�F�b�N<BR>
        //     * �@@this.�h�c��null�ł������ꍇ�A�uID��null�v�̗�O���X���[����B<BR>
        //     *   class: WEB3BusinessLayerException<BR>
        //     *   tag:   BUSINESS_ERROR_00080<BR>
        //
        if (id==null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,STR_METHOD_NAME);
        }
		// �R�j�@@���������`�F�b�N<BR>
		//     * �@@�R�|�P�jthis.����������null�ł���΁A�u�������������w��v�̗�O���X���[����B<BR>
		//     *   class: WEB3BusinessLayerException<BR>
		//     *   tag:   BUSINESS_ERROR_00126<BR>
		if (this.orderQuantity == null)
		{
			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00126,STR_METHOD_NAME);
		}

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4140453A0084
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginOpenMarginChangeCompleteResponse(this);
    }    
}
@
