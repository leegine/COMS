head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistSellTransSrcCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����E���z���E���p����U���挟�������N���X(WEB3AdminInformProfDistSellTransSrcCondition.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/24 �Ӑ�(���u) �V�K�쐬 ���f��No.045
*/

package webbroker3.inform.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����E���z���E���p����U���挟�������N���X)<BR>
 * �����E���z���E���p����U���挟�������N���X<BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcCondition extends Message
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistSellTransSrcCondition.class);

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;

    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h
     */
    public String traderCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;

    /**
     * (�w��敪)<BR>
     * �w��敪
     */
    public String specifyDiv;

    /**
     * (���i)<BR>
     * ���i
     */
    public String product;

    /**
     * (�U�֋敪)<BR>
     * �U�֋敪
     */
    public String transferDiv;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;

    /**
     * (�o�^���i���j)<BR>
     * �o�^���i���j
     */
    public Date registDateFrom;

    /**
     * (�o�^���i���j)<BR>
     * �o�^���i���j
     */
    public Date registDateTo;

    /**
     * @@roseuid 465593750398
     */
    public WEB3AdminInformProfDistSellTransSrcCondition()
    {

    }

    /**
     * �������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���X�R�[�h<BR>
     * <BR>
     * �@@�P�|�P�j�ȉ��̏ꍇ�A��O�́u���X�R�[�h�G���[�v��throw����B<BR>
     * <BR>
     * �@@�@@���X�R�[�h != null and<BR>
     * �@@�@@���X�R�[�h != ���p����<BR>
     * �@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00779<BR>
     * <BR>
     * �Q�j���҃R�[�h<BR>
     * <BR>
     * �@@�Q�|�P�j�ȉ��̏ꍇ�A��O�́u���҃R�[�h�G���[�v��throw����B<BR>
     * <BR>
     * �@@�@@���҃R�[�h != null and<BR>
     * �@@�@@���҃R�[�h != ���p����<BR>
     * �@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@:�@@BUSINESS_ERROR_02782<BR>
     * <BR>
     * �R�j�ڋq�R�[�h<BR>
     * <BR>
     * �@@�R�|�P�j�ȉ��̏ꍇ�A��O�́u�ڋq�R�[�h�G���[�v��throw����B<BR>
     * <BR>
     * �@@�@@�ڋq�R�[�h != null and<BR>
     * �@@�@@�ڋq�R�[�h != ���p����<BR>
     * �@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00780<BR>
     * <BR>
     * �S�j�����R�[�h<BR>
     * <BR>
     * �@@�S�|�P�j�ȉ��̏ꍇ�A��O�́u�����R�[�h�G���[�v��throw����B<BR>
     * <BR>
     * �@@�@@�����R�[�h != null and<BR>
     * �@@�@@�����R�[�h != ���p����<BR>
     * �@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@:�@@BUSINESS_ERROR_01067<BR>
     * <BR>
     * �T�j�o�^��<BR>
     * <BR>
     * �@@�T�|�P�j�ȉ��̏ꍇ�A��O�́u���t�G���[�v��throw����B<BR>
     * <BR>
     * �@@�@@�o�^���i���j�@@!=�@@null�@@and<BR>
     * �@@�@@�o�^���i���j�@@!=�@@null�@@and<BR>
     * �@@�@@�o�^���i���j�@@> �o�^���i���j<BR>
     * �@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@:�@@BUSINESS_ERROR_02222<BR>
     * @@throws WEB3BaseException
     * @@roseuid 461F213A007B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // �P�|�P�j�ȉ��̏ꍇ�A��O�́u���X�R�[�h�G���[�v��throw����B
        // ���X�R�[�h != null and ���X�R�[�h != ���p����
        if (this.branchCode != null && !WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("���X�R�[�h�̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h�̓��͂��s���ł��B");
        }

        // �Q�|�P�j�ȉ��̏ꍇ�A��O�́u���҃R�[�h�G���[�v��throw����B
        // ���҃R�[�h != null and ���҃R�[�h != ���p����
        if (this.traderCode != null && !WEB3StringTypeUtility.isDigit(this.traderCode))
        {
            log.debug("���҃R�[�h�i������j�̒������s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02782,
                this.getClass().getName() + STR_METHOD_NAME,
                "���҃R�[�h�G���[�B");
        }

        // �R�|�P�j�ȉ��̏ꍇ�A��O�́u�ڋq�R�[�h�G���[�v��throw����B
        // �ڋq�R�[�h != null and �ڋq�R�[�h != ���p����
        if (this.accountCode != null && !WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("�ڋq�R�[�h�̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ڋq�R�[�h�̓��͂��s���ł��B");
        }

        // �S�|�P�j�ȉ��̏ꍇ�A��O�́u�����R�[�h�G���[�v��throw����B
        // �����R�[�h != null and �����R�[�h != ���p����
        if (this.productCode != null && !WEB3StringTypeUtility.isDigit(this.productCode))
        {
            log.debug("�����R�[�h�̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����R�[�h�̓��͂��s���ł��B");
        }

        // �T�|�P�j�ȉ��̏ꍇ�A��O�́u���t�G���[�v��throw����B
        // �o�^���i���j != null and �o�^���i���j != null and �o�^���i���j > �o�^���i���j
        if (registDateFrom != null && registDateTo != null && (WEB3DateUtility.compare(registDateFrom, registDateTo) > 0))
        {
            log.debug("�o�^���i���j���o�^���i���j�𒴂��Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02222,
                this.getClass().getName() + STR_METHOD_NAME,
                "�o�^���i���j���o�^���i���j�𒴂��Ă��܂��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
