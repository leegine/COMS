head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�������̓��N�G�X�g�N���X(WEB3MstkSellInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���C�g (���u) �V�K�쐬
                   2004/12/13 �����a�� (SRA) �c�Č��Ή� �m��.�S�O�V
                   2005/01/05 �����a�� (SRA) JavaDoc�C��
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����~�j�������t�������̓��N�G�X�g�j�B<BR>
 * <BR>
 * �����~�j�������t�������̓��N�G�X�g�N���X
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3MstkSellInputRequest extends WEB3GenRequest 
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkSellInputRequest.class);
    
    /**
     * �iPTYPE�j�B
     */
    public static final String PTYPE = "mstk_sellInput";

    /**
     * �iSerialVersionUID�j�B
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * �i�����R�[�h�j�B
     */
    public String productCode;
    
    /**
     * �i�����~�j�������t�������̓��N�G�X�g�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     */
    public WEB3MstkSellInputRequest() 
    {
    }
    
    /**
     * �ivalidate�j�B<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�@@�����R�[�h�`�F�b�N<BR>
     * �@@�@@�@@�@@this.�����R�[�h���A�ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@�ȉ��̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�����R�[�h��null(�u�����R�[�h��null�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00079
     */
    public void validate() throws WEB3BaseException
    {
     
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        log.debug("�����R�[�h�`�F�b�N");
        if(productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079, 
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreate���X�|���X�j�B<BR>
     * <BR>
     * @@return WEB3GenResponse �����~�j�������t�������̓��X�|���X
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkSellInputResponse(this);
    }
}
@
