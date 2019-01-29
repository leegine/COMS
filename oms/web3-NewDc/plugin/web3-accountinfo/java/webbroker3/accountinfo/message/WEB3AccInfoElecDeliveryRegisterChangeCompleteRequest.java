head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �d�q��t�T�[�r�X�o�^�E�ύX�������N�G�X�g(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 �����F(���u) �V�K�쐬 �d�l�ύX���f��No.277
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoReportDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�d�q��t�T�[�r�X�o�^�E�ύX�������N�G�X�g)<BR>
 * �d�q��t�T�[�r�X�o�^�E�ύX�������N�G�X�g�N���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "elec_delivery_register_change_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 201011121538L;

    /**
     * (����񍐏���t�敪)<BR>
     * ����񍐏���t�敪 <BR>
     * <BR>
     * 0�F�@@�X�֔z�z<BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String tradingReportDiv;

    /**
     * (����c���񍐏���t�敪)<BR>
     * ����c���񍐏���t�敪<BR>
     * <BR>
     * 0�F�@@�X�֔z�z<BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String positionReportDiv;

    /**
     * (�^�p�񍐏���t�敪)<BR>
     * �^�p�񍐏���t�敪<BR>
     * <BR>
     * 0�F�@@�X�֔z�z<BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String opeReportDiv;

    /**
     * (�񊼁E�K��W�񍐏���t�敪)<BR>
     * �񊼁E�K��W�񍐏���t�敪<BR>
     * <BR>
     * 0�F�@@�X�֔z�z<BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String ordRulReportDiv;

    /**
     * (���ʌ�t�敪�P)<BR>
     * ���ʌ�t�敪�P<BR>
     * <BR>
     * 0�F�@@�X�֔z�z<BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String report_div1;

    /**
     * (���ʌ�t�敪�Q)<BR>
     * ���ʌ�t�敪�Q<BR>
     * <BR>
     * 0�F�@@�X�֔z�z<BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String report_div2;

    /**
     * (���ʌ�t�敪�R)<BR>
     * ���ʌ�t�敪�R<BR>
     * <BR>
     * 0�F�@@�X�֔z�z<BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String report_div3;

    /**
     * (���ʌ�t�敪�S)<BR>
     * ���ʌ�t�敪�S<BR>
     * <BR>
     * 0�F�@@�X�֔z�z<BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String report_div4;

    /**
     * (���ʌ�t�敪�T)<BR>
     * ���ʌ�t�敪�T<BR>
     * <BR>
     * 0�F�@@�X�֔z�z<BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String report_div5;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest()
    {

    }

    /**
     * �P�j�@@����񍐏���t�敪�`�F�b�N <BR>
     * �@@�@@�@@�@@���N�G�X�g.����񍐏���t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u����񍐏���t�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E0�F�X�֔z�z�@@ <BR>
     * �@@�@@�@@�@@�E1�F�d�q�z�z <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03211<BR>
     * <BR>
     * �Q�j�@@����c���񍐏���t�敪�`�F�b�N <BR>
     * �@@�@@�@@�@@���N�G�X�g.����c���񍐏���t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u����c���񍐏���t�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E0�F�X�֔z�z�@@�@@ <BR>
     * �@@�@@�@@�@@�E1�F�d�q�z�z <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03212<BR>
     * <BR>
     * �R�j�@@�^�p�񍐏���t�敪�`�F�b�N <BR>
     * �@@�@@�@@�@@���N�G�X�g.�^�p�񍐏���t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�^�p�񍐏���t�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E0�F�X�֔z�z�@@ <BR>
     * �@@�@@�@@�@@�E1�F�d�q�z�z <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03213<BR>
     * <BR>
     * �S�j�@@�񊼁E�K��W�񍐏���t�敪�`�F�b�N <BR>
     * �@@�@@�@@�@@���N�G�X�g.�񊼁E�K��W�񍐏���t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�񊼁E�K��W�񍐏���t�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E0�F�X�֔z�z�@@ <BR>
     * �@@�@@�@@�@@�E1�F�d�q�z�z <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03214<BR>
     * <BR>
     * �T�j�@@���ʂP��t�敪�`�F�b�N <BR>
     * �@@�@@�@@�@@���N�G�X�g.���ʂP��t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���ʂP��t�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E0�F�X�֔z�z�@@ <BR>
     * �@@�@@�@@�@@�E1�F�d�q�z�z <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03215<BR>
     * <BR>
     * �U�j�@@���ʂQ��t�敪�`�F�b�N �@@�@@ <BR>
     * �@@�@@�@@�@@���N�G�X�g.���ʂQ��t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���ʂQ��t�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E0�F�X�֔z�z�@@ <BR>
     * �@@�@@�@@�@@�E1�F�d�q�z�z <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03216<BR>
     * <BR>
     * �V�j�@@���ʂR��t�敪�`�F�b�N <BR>
     * �@@�@@�@@�@@���N�G�X�g.���ʂR��t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���ʂR��t�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E0�F�X�֔z�z�@@ <BR>
     * �@@�@@�@@�@@�E1�F�d�q�z�z <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03217<BR>
     * <BR>
     * �W�j�@@���ʂS��t�敪�`�F�b�N <BR>
     * �@@�@@�@@�@@���N�G�X�g.���ʂS��t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���ʂS��t�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E0�F�X�֔z�z�@@ <BR>
     * �@@�@@�@@�@@�E1�F�d�q�z�z <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03218<BR>
     * <BR>
     * �X�j�@@���ʂT��t�敪�`�F�b�N <BR>
     * �@@�@@�@@�@@���N�G�X�g.���ʂT��t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���ʂT��t�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E0�F�X�֔z�z�@@ <BR>
     * �@@�@@�@@�@@�E1�F�d�q�z�z <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03219<BR>
     * <BR>
     * �P�O�j�����������Ă��邩�̃`�F�b�N <BR>
     * �@@�@@�@@���L������S�Ė������ꍇ�A������������Ă��Ȃ��Ɣ��f���A <BR>
     * �@@�@@�@@�@@��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@���N�G�X�g.����񍐏���t�敪��null <BR>
     * �@@�@@�@@�@@�A���N�G�X�g.����c���񍐏���t�恁null <BR>
     * �@@�@@�@@�@@�B���N�G�X�g.�^�p�񍐏���t�敪��null <BR>
     * �@@�@@�@@�@@�C���N�G�X�g.�񊼁E�K��W�񍐏���t�敪��null <BR>
     * �@@�@@�@@�@@�D���N�G�X�g.���ʂP��t�敪��null <BR>
     * �@@�@@�@@�@@�E���N�G�X�g.���ʂQ��t�敪��null <BR>
     * �@@�@@�@@�@@�F���N�G�X�g.���ʂR��t�敪��null <BR>
     * �@@�@@�@@�@@�G���N�G�X�g.���ʂS��t�敪��null <BR>
     * �@@�@@�@@�@@�H���N�G�X�g.���ʂT��t�敪��null <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03220<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@����񍐏���t�敪�`�F�b�N
        //�@@�@@�@@�@@���N�G�X�g.����񍐏���t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u����񍐏���t�敪������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E0�F�X�֔z�z
        //�@@�@@�@@�@@�E1�F�d�q�z�z
        if (this.tradingReportDiv != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.tradingReportDiv)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.tradingReportDiv))
        {
            log.debug("����񍐏���t�敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03211,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����񍐏���t�敪������`�̒l�ł��B");
        }

        //�Q�j�@@����c���񍐏���t�敪�`�F�b�N
        //�@@�@@�@@�@@���N�G�X�g.����c���񍐏���t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u����c���񍐏���t�敪������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E0�F�X�֔z�z
        //�@@�@@�@@�@@�E1�F�d�q�z�z
        if (this.positionReportDiv != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.positionReportDiv)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.positionReportDiv))
        {
            log.debug("����c���񍐏���t�敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03212,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����c���񍐏���t�敪������`�̒l�ł��B");
        }

        //�R�j�@@�^�p�񍐏���t�敪�`�F�b�N
        //�@@�@@�@@�@@���N�G�X�g.�^�p�񍐏���t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u�^�p�񍐏���t�敪������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E0�F�X�֔z�z
        //�@@�@@�@@�@@�E1�F�d�q�z�z
        if (this.opeReportDiv != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.opeReportDiv)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.opeReportDiv))
        {
            log.debug("�^�p�񍐏���t�敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03213,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�^�p�񍐏���t�敪������`�̒l�ł��B");
        }

        //�S�j�@@�񊼁E�K��W�񍐏���t�敪�`�F�b�N
        //�@@�@@�@@�@@���N�G�X�g.�񊼁E�K��W�񍐏���t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u�񊼁E�K��W�񍐏���t�敪������`�̒l�ł��B�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E0�F�X�֔z�z
        //�@@�@@�@@�@@�E1�F�d�q�z�z
        if (this.ordRulReportDiv != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.ordRulReportDiv)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.ordRulReportDiv))
        {
            log.debug("�񊼁E�K��W�񍐏���t�敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03214,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�񊼁E�K��W�񍐏���t�敪������`�̒l�ł��B");
        }

        //�T�j�@@���ʂP��t�敪�`�F�b�N
        //�@@�@@�@@�@@���N�G�X�g.���ʂP��t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u���ʂP��t�敪������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E0�F�X�֔z�z
        //�@@�@@�@@�@@�E1�F�d�q�z�z
        if (this.report_div1 != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.report_div1)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.report_div1))
        {
            log.debug("���ʂP��t�敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03215,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʂP��t�敪������`�̒l�ł��B");
        }

        //�U�j�@@���ʂQ��t�敪�`�F�b�N
        //�@@�@@�@@�@@���N�G�X�g.���ʂQ��t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u���ʂQ��t�敪������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E0�F�X�֔z�z
        //�@@�@@�@@�@@�E1�F�d�q�z�z
        if (this.report_div2 != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.report_div2)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.report_div2))
        {
            log.debug("���ʂQ��t�敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03216,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʂQ��t�敪������`�̒l�ł��B");
        }

        //�V�j�@@���ʂR��t�敪�`�F�b�N
        //�@@�@@�@@�@@���N�G�X�g.���ʂR��t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u���ʂR��t�敪������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E0�F�X�֔z�z
        //�@@�@@�@@�@@�E1�F�d�q�z�z
        if (this.report_div3 != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.report_div3)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.report_div3))
        {
            log.debug("���ʂR��t�敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03217,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʂR��t�敪������`�̒l�ł��B");
        }

        //�W�j�@@���ʂS��t�敪�`�F�b�N
        //�@@�@@�@@�@@���N�G�X�g.���ʂS��t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u���ʂS��t�敪������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E0�F�X�֔z�z
        //�@@�@@�@@�@@�E1�F�d�q�z�z
        if (this.report_div4 != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.report_div4)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.report_div4))
        {
            log.debug("���ʂS��t�敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03218,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʂS��t�敪������`�̒l�ł��B");
        }

        //�X�j�@@���ʂT��t�敪�`�F�b�N
        //�@@�@@�@@�@@���N�G�X�g.���ʂT��t�敪��null�A�����L�̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u���ʂT��t�敪������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E0�F�X�֔z�z
        //�@@�@@�@@�@@�E1�F�d�q�z�z
        if (this.report_div5 != null
            && !WEB3AccInfoReportDivDef.MAIL.equals(this.report_div5)
            && !WEB3AccInfoReportDivDef.ELECTRON.equals(this.report_div5))
        {
            log.debug("���ʂT��t�敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03219,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʂT��t�敪������`�̒l�ł��B");
        }

        //�P�O�j�����������Ă��邩�̃`�F�b�N
        //�@@�@@�@@���L������S�Ė������ꍇ�A������������Ă��Ȃ��Ɣ��f���A
        //�@@�@@�@@�@@��O���X���[����B
        //�@@�@@�@@�@@�@@���N�G�X�g.����񍐏���t�敪��null
        //�@@�@@�@@�@@�A���N�G�X�g.����c���񍐏���t�恁null
        //�@@�@@�@@�@@�B���N�G�X�g.�^�p�񍐏���t�敪��null
        //�@@�@@�@@�@@�C���N�G�X�g.�񊼁E�K��W�񍐏���t�敪��null
        //�@@�@@�@@�@@�D���N�G�X�g.���ʂP��t�敪��null
        //�@@�@@�@@�@@�E���N�G�X�g.���ʂQ��t�敪��null
        //�@@�@@�@@�@@�F���N�G�X�g.���ʂR��t�敪��null
        //�@@�@@�@@�@@�G���N�G�X�g.���ʂS��t�敪��null
        //�@@�@@�@@�@@�H���N�G�X�g.���ʂT��t�敪��null
        if (this.tradingReportDiv == null
            && this.positionReportDiv == null
            && this.opeReportDiv == null
            && this.ordRulReportDiv == null
            && this.report_div1 == null
            && this.report_div2 == null
            && this.report_div3 == null
            && this.report_div4 == null
            && this.report_div5 == null)
        {
            log.debug("�\���Ώۏ��ʂ��I������Ă��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03220,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\���Ώۏ��ʂ��I������Ă��܂���B");
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
        return new WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse(this);
    }
}
@
