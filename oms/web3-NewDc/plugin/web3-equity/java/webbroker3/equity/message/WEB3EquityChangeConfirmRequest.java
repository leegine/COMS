head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������������m�F�v�����N�G�X�g�f�[�^�N���X(WEB3EquityChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/11 ����� (���u) �V�K�쐬
Revesion History : 2004/12/08 �����a��(SRA) �c�Č��Ή� �m��.�O�T�V
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����������������m�F���N�G�X�g�j�B<br>
 * <br>
 * �����������������m�F�v���@@���N�G�X�g�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityChangeConfirmRequest extends WEB3EquityCommonRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeConfirmRequest.class);

    /**
     * (ID)<BR>
     * �����ΏۂƂ��钍���f�[�^����ӂɓ��肷�����<BR>
     */
    public String id;

    /**
     * �|�������t�B�b�N�^�C�v�B<BR>
     */
    public static final String PTYPE = "equity_change_confirm";

    /**
     * �V���A���o�[�W����UID�B<BR>
     */
    public static final long serialVersionUID = 200402241455L;

    /**
     * �R���X�g���N�^
     * @@roseuid 406120FA03D4
     */
    public WEB3EquityChangeConfirmRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B
     * @@return WEB3EquityChangeConfirmResponse
     * @@roseuid 4061202402BB
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityChangeConfirmResponse();
    }
    
    /**
     * (validate)<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B<BR>
     * <BR>
     * �Q�j�@@ID�`�F�b�N<BR>
     * �@@this.ID��null�̏ꍇ�A<BR>
     * �@@�uID��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00953<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);

        super.validate();
        
        if(this.id == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + ".validate()");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
