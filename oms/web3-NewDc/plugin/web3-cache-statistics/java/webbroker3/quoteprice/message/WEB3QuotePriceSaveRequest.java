head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceSaveRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���X�|���X�N���X(WEB3QuotePriceSaveRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/06 �� (FLJ)�V�K�쐬
 */

package webbroker3.quoteprice.message;

import com.fitechlabs.xtrade.kernel.message.*;
import webbroker3.common.*;
import webbroker3.util.*;

/**
 * �i���X�|���X�N���X�X�N���X�j�B<br>
 * <br>
 * ���X�|���X�N���X
 * @@author �� (FLJ)
 * @@version 1.0
 */
public class WEB3QuotePriceSaveRequest
    extends Message
{

    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuotePriceSaveRequest.class);

    /**
     * (��ЃR�[�h)
     */
    public String institutionCd;

    /**
     * (From�����R�[�hD)
     */
    public Long fromProductCd;

    /**
     * (To�����R�[�hD)
     */
    public Long toProductCd;

    /**
     * �ivalidate�j<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@this.�X���b�h�m����null�܂��́A�e�����������h�c=null�܂��́ATo�����h�c=null�̏ꍇ�A<BR>
     * ��O��throw����B<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.fromProductCd == null ||
            this.toProductCd == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01291,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (this.institutionCd == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01974,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3QuotePriceSaveRequest()
    {
    }

}
@
