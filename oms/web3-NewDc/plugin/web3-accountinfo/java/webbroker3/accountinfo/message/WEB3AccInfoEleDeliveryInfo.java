head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoEleDeliveryInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �d�q��t���(WEB3AccInfoEleDeliveryInfo.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 �����F(���u) �V�K�쐬 �d�l�ύX���f��No.277 280
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�d�q��t���)<BR>
 * �d�q��t���N���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AccInfoEleDeliveryInfo extends Message
{
    /**
     * (���ʂT��t�敪�X�V����)<BR>
     * ���ʂT��t�敪�X�V����
     */
    public Date reportDivUpdateDate5;

    /**
     * (���ʂT�\���敪)<BR>
     * ���ʂT�\���敪 <BR>
     * <BR>
     * 0�F�@@�\���� <BR>
     * 1�F�@@�\������<BR>
     */
    public String reportRegDiv5;

    /**
     * (���ʂT��t�敪)<BR>
     * ���ʂT��t�敪 <BR>
     * <BR>
     * 0�F�@@�X�֔z�z <BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String reportDiv5;

    /**
     * (���ʂS��t�敪�X�V����)<BR>
     * ���ʂS��t�敪�X�V����
     */
    public Date reportDivUpdateDate4;

    /**
     * (���ʂS�\���敪)<BR>
     * ���ʂS�\���敪 <BR>
     * <BR>
     * 0�F�@@�\���� <BR>
     * 1�F�@@�\������<BR>
     */
    public String reportRegDiv4;

    /**
     * (���ʂS��t�敪)<BR>
     * ���ʂS��t�敪 <BR>
     * <BR>
     * 0�F�@@�X�֔z�z <BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String reportDiv4;

    /**
     * (���ʂR��t�敪�X�V����)<BR>
     * ���ʂR��t�敪�X�V����
     */
    public Date reportDivUpdateDate3;

    /**
     * (���ʂR�\���敪)<BR>
     * ���ʂR�\���敪 <BR>
     * <BR>
     * 0�F�@@�\���� <BR>
     * 1�F�@@�\������<BR>
     */
    public String reportRegDiv3;

    /**
     * (���ʂR��t�敪)<BR>
     * ���ʂR��t�敪 <BR>
     * <BR>
     * 0�F�@@�X�֔z�z <BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String reportDiv3;
    
    /**
     * (���ʂQ��t�敪�X�V����)<BR>
     * ���ʂQ��t�敪�X�V����
     */
    public Date reportDivUpdateDate2;

    /**
     * (���ʂQ�\���敪)<BR>
     * ���ʂQ�\���敪 <BR>
     * <BR>
     * 0�F�@@�\���� <BR>
     * 1�F�@@�\������<BR>
     */
    public String reportRegDiv2;

    /**
     * (���ʂQ��t�敪)<BR>
     * ���ʂQ��t�敪 <BR>
     * <BR>
     * 0�F�@@�X�֔z�z <BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String reportDiv2;

    /**
     * (���ʂP��t�敪�X�V����)<BR>
     * ���ʂP��t�敪�X�V����
     */
    public Date reportDivUpdateDate1;

    /**
     * (���ʂP�\���敪)<BR>
     * ���ʂP�\���敪 <BR>
     * <BR>
     * 0�F�@@�\���� <BR>
     * 1�F�@@�\������<BR>
     */
    public String reportRegDiv1;

    /**
     * (���ʂP��t�敪)<BR>
     * ���ʂP��t�敪 <BR>
     * <BR>
     * 0�F�@@�X�֔z�z <BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String reportDiv1;

    /**
     * (�񊼁E�K��W�񍐏��\����ԋ敪)<BR>
     * �񊼁E�K��W�񍐏��\����ԋ敪 <BR>
     * <BR>
     * 0�F�@@�\���� <BR>
     * 1�F�@@�\������<BR>
     */
    public String ordRulRepRegDiv;

    /**
     * (�񊼁E�K��W�񍐏���t��ԋ敪�X�V����)<BR>
     * �񊼁E�K��W�񍐏���t��ԋ敪�X�V����<BR>
     */
    public Date ordRulReportDivUpdateDate;

    /**
     * (�񊼁E�K��W�񍐏���t��ԋ敪)<BR>
     * �񊼁E�K��W�񍐏���t��ԋ敪 <BR>
     * <BR>
     * 0�F�@@�X�֔z�z<BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String ordRulReportDiv;

    /**
     * (����񍐏��\����ԋ敪)<BR>
     * ����񍐏��\����ԋ敪 <BR>
     * <BR>
     * 0�F�@@�\���� <BR>
     * 1�F�@@�\������ <BR>
     */
    public String tradingReportRegDiv;

    /**
     * (����񍐏���t��ԋ敪�X�V����)<BR>
     * ����񍐏���t��ԋ敪�X�V����<BR>
     */
    public Date tradingReportDivUpdateDate;

    /**
     * (����c���񍐏��\����ԋ敪)<BR>
     * ����c���񍐏��\����ԋ敪 <BR>
     * <BR>
     * 0�F�@@�\���� <BR>
     * 1�F�@@�\������ <BR>
     */
    public String positionReportRegDiv;

    /**
     * (����c���񍐏���t��ԋ敪�X�V����)<BR>
     * ����c���񍐏���t��ԋ敪�X�V����<BR>
     */
    public Date positionReportDivUpdateDate;

    /**
     * (�^�p�񍐏��\����ԋ敪)<BR>
     * �^�p�񍐏��\����ԋ敪 <BR>
     * <BR>
     * 0�F�@@�\���� <BR>
     * 1�F�@@�\������ <BR>
     */
    public String opeReportRegDiv;

    /**
     * (�^�p�񍐏���t��ԋ敪�X�V����)<BR>
     * �^�p�񍐏���t��ԋ敪�X�V����<BR>
     */
    public Date opeReportDivUpdateDate;

    /**
     * (�^�p�񍐏���t��ԋ敪)<BR>
     * �^�p�񍐏���t��ԋ敪 <BR>
     * <BR>
     * 0�F�@@�X�֔z�z <BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String opeReportDiv;

    /**
     * (����񍐏���t��ԋ敪)<BR>
     * ����񍐏���t��ԋ敪<BR>
     * <BR>
     * 0�F�@@�X�֔z�z <BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String tradingReportDiv;

    /**
     * (����c���񍐏���t��ԋ敪)<BR>
     * ����c���񍐏���t��ԋ敪<BR>
     * <BR>
     * 0�F�@@�X�֔z�z <BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String positionReportDiv;

    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3AccInfoEleDeliveryInfo()
    {

    }
}
@
