head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.58.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3HostRequestCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L���[�e�[�u���f�[�^�R�[�h �萔��`�C���^�t�F�C�X(WEB3HostRequestCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/24 �����@@���F(SRA) �V�K�쐬
Revesion History : 2006/06/29 �h�C �y�����J�݁z�d�l�ύX�Ǘ��䒠�E�c�a���C�A�E�g026��Ή�
Revesion History : 2006/07/12 �h�C �y�����J�݁z�d�l�ύX�Ǘ��䒠�E�c�a���C�A�E�g028��Ή�
Revesion History : 2006/08/30 �h�C(���u)  �c�a���C�A�E�g(�O�ݓ��o���e�[�u��)�ɂ��
Revesion History : 2006/08/30 �h�C(���u)  �c�a���C�A�E�g(�O�ݓ��o���`�[�L���[�e�[�u��)�ɂ��
Revesion History : 2006/09/05 �h�C(���u)  �c�a���C�A�E�g(�O�ݓ��o���`�[��t�L���[�e�[�u��)�ɂ��
Revesion History : 2006/09/06 �h�C �y�����J�݁z�d�l�ύX�Ǘ��䒠�E�c�a���C�A�E�g033��Ή�
Revesion History : 2006/09/11 �h�C �y�����J�݁z�d�l�ύX�Ǘ��䒠�E�c�a���C�A�E�g034��Ή�
Revesion History : 2007/02/08 �h�C �c�a���C�A�E�g(�O��MMF�����L���[�e�[�u��)�ɂ��
Revesion History : 2007/06/13 �h�C �y���ʁz�d�l�ύX�Ǘ��䒠�E�c�a���C�A�E�g500��Ή�
Revesion History : 2007/09/20 �ЋŃV�� �y�A���Ǘ��z���o�C����p�����Q���Ή�
Revesion History : 2008/12/31 ��іQ �y���Ǘ��ҁz�d�l�ύX�Ǘ��䒠�c�a���C�A�E�g024
Revesion History : 2009/08/13 ��іQ �y�����J�݁z�d�l�ύX�Ǘ��䒠�E�c�a���C�A�E�g055��Ή�
Revesion History : 2009/09/04 ��іQ �y�����J�݁z�d�l�ύX�Ǘ��䒠�E�c�a���C�A�E�g063��Ή�
*/
package webbroker3.common.define;

/**
 * �L���[�e�[�u���f�[�^�R�[�h�萔��`�C���^�t�F�C�X�B<BR>
 *
 * @@author �����@@���F(SRA)
 * @@version 1.0
 */
public interface WEB3HostRequestCodeDef
{
    /**
     * ����������������ʒm�B<BR>
     *
     */
    public static final String EQUITY_CHANGE_CANCEL_NOTICE = "AI817";

    /**
     * ������������B<BR>
     *
     */
    public static final String EQUITY_ORDER = "AI801";

    /**
     * ����������t�B<BR>
     *
     */
    public static final String EQUITY_ORDER_ACCEPT = "AI80A";

    /**
     * ����������������B<BR>
     *
     */
    public static final String EQUITY_CHANGE_CANCEL = "AI80B";

    /**
     * �����o���ʒm�B<BR>
     */
    public static final String EQUITY_ORDER_EXEC_NOTICE = "AI811";

    /**
     * ���������ʒm�B<BR>
     */
    public static final String EQUITY_ORDER_CLOSE_NOTICE = "AI813";

    /**
     * �����o���I���ʒm�B<BR>
     */
    public static final String EQUITY_ORDER_EXEC_END_NOTICE = "AI814";

    /**
     * �����������͒ʒm�B<BR>
     */
    public static final String EQUITY_ORDER_INPUT_NOTICE = "AI821";

    /**
     * �������������B<BR>
     */
    public static final String EQUITY_ORDER_CHANGE = "AI802";

    /**
     * ������������B<BR>
     */
    public static final String EQUITY_ORDER_CANCEL = "AI802";

    /**
     * �����������n�����B<BR>
     */
    public static final String EQUITY_SPOT_ORDER = "AI805";

    /**
     * �����������n������������B<BR>
     */
    public static final String EQUITY_SPOT_ORDER_CHANGE_CANCEL = "AI808";

    /**
     * �����w�薄�w���B<BR>
     */
    public static final String EQUITY_ORDER_DESIGNATE = "AI806";

    /**
     * ��������������t�B<BR>
     */
    public static final String EQUITY_ORDER_CHANGE_ACCEPT = "AI80B";

    /**
     * �������������t�B<BR>
     */
    public static final String EQUITY_ORDER_CANCEL_ACCEPT = "AI80B";

    /**
     * �����������n������t�B<BR>
     */
    public static final String EQUITY_SPOT_ORDER_ACCEPT = "AI80E";

    /**
     * �����������n�������������t�B<BR>
     */
    public static final String EQUITY_SPOT_ORDER_CHANGE_CANCEL_ACCEPT = "AI80H";

    /**
     * ���������ʒm�B<BR>
     */
    public static final String EQUITY_ORDER_NOTICE = "AI821";

    /**
     * �����J�z�X�L�b�v�����ʒm�B<BR>
     */
    public static final String EQUITY_TRANSFE_PRODUCT_NOTICE = "AI822";

    /**
     * �������n���͒ʒm�B<BR>
     */
    public static final String MARGIN_SWAP_INPUT_NOTICE = "AI826";
    

    /* �敨�I�v�V�����f�[�^�R�[�h���̈ꗗ */

    /* ��菈�� */

    /** 
     * �����w���I�v�V�������� 
     */
    public static final String OPTION_ORDER = "EI801";

    /** 
     * �����w���I�v�V������������ & �����w���I�v�V����������� 
     */
    public static final String OPTION_ORDER_CHANGE_CANCEL = "EI802";

    /** 
     * �����w���敨���� 
     */
    public static final String FUTURES_ORDER = "EI803";

    /** 
     * �����w���敨�������� & �����w���敨������� 
     */
    public static final String FUTURES_ORDER_CHANGE_CANCEL = "EI804";

    /** 
     * �����w���I�v�V�����w�薄�w�� 
     */
    public static final String OPTION_DESIGNATE_EMBEDDED_INDICATION = "EI806";

    /** 
     * �����w���敨�w�薄�w�� 
     */
    public static final String FUTURES_DESIGNATE_EMBEDDED_INDICATION = "EI807";

    /* ���菈�� */

    /** 
     * �����w���I�v�V����������t 
     */
    public static final String OPTION_ORDER_ACCEPT = "EI80A";

    /** 
     * �����w���I�v�V��������������t & �����w���I�v�V�������������t 
     */
    public static final String OPTION_ORDER_CHANGE_CANCEL_ACCEPT = "EI80B";

    /** 
     * �����w���敨������t 
     */
    public static final String FUTURES_ORDER_ACCEPT = "EI80C";

    /** 
     * �����w���敨����������t & �����w���敨���������t
     */
    public static final String FUTURES_ORDER_CHANGE_CANCEL_ACCEPT = "EI80D";

    /** 
     * �����w���I�v�V�����o���ʒm 
     */
    public static final String OPTION_EXEC_NOTICE = "EI811";

    /** 
     * �����w���I�v�V������������ʒm 
     */
    public static final String OPTION_CHANGE_CANCEL_NOTICE = "EI812";

    /** 
     * �����w���I�v�V���������ʒm 
     */
    public static final String OPTION_CLOSE_NOTICE = "EI813";

    /** 
     * �����w���I�v�V�����o���I���ʒm 
     */
    public static final String OPTION_EXEC_END_NOTICE = "EI814";

    /** 
     * �����w���敨�o���ʒm 
     */
    public static final String FUTURES_EXEC_NOTICE = "EI815";

    /** 
     * �����w���敨��������ʒm 
     */
    public static final String FUTURES_CHANGE_CANCEL_NOTICE = "EI816";

    /** 
     * �����w���敨�����ʒm 
     */
    public static final String FUTURES_CLOSE_NOTICE = "EI817";

    /** 
     * (MAXAS-AP�FOP�����ʒm)�Œ�
     */
    public static final String OPTION_ORDER_NOTICE = "EI821";    
    
    /** 
     * (MAXAS-AP�F�敨�����ʒm)�Œ�
     */
    public static final String FUTURES_ORDER_NOTICE = "EI822";

    /**
     * �����w���I�v�V�����[��o���I��
     */
    public static final String OPTION_EVENING_SESSION_END = "EI823";

    /**
     * �����w���敨�[��o���I��
     */
    public static final String FUTURES_EVENING_SESSION_END = "EI824";

    /** 
     * �����w���敨�o���I���ʒm 
     */
    public static final String FUTURES_EXEC_END_NOTICE = "EI818";

    /* �ݐϓ����f�[�^�R�[�h���̈ꗗ */
    
    /** 
     * �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�iMRF���j 
     */
    public static final String RUITO_REQUEST_MESSAGE_SEND_MRF_SELL = "GI831";

    /** 
     * �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t�j 
     */
    public static final String RUITO_REQUEST_MESSAGE_SEND_BUY = "DI801";

    /** 
     * �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���j
     */
    public static final String RUITO_REQUEST_MESSAGE_SEND_SELL = "DI803";

    /* �����M���f�[�^�R�[�h���̈ꗗ */
    
    /** 
     * �������M���� 
     */
    public static final String MUTUAL_FUND_DOMESTIC_ORDER = "CI801";

    /** 
     * �������M������� 
     */
    public static final String MUTUAL_FUND_DOMESTIC_ORDER_CANCEL = "CI802";

    /** 
     * �O�����M���� 
     */
    public static final String MUTUAL_FUND_FOREIGN_ORDER = "CI803";

    /** 
     * �O�����M������� 
     */
    public static final String MUTUAL_FUND_FOREIGN_ORDER_CANCEL = "CI804";

    /** 
     * ���M�抷���� 
     */
    public static final String MUTUAL_FUND_SWITCHING_ORDER = "CI805";

    /** 
     * ���M�抷�������	
     */
    public static final String MUTUAL_FUND_SWITCHING_ORDER_CANCEL = "CI806";

    /**
     * CI807�F��W
     */
    public static final String MUTUAL_FUND_RECRUIT = "CI807";

    /** 
     * �������M������t	
     */
    public static final String MUTUAL_FUND_DOMESTIC_ORDER_ACCEPT = "CI80A";

    /** 
     * �������M���������t	
     */
    public static final String MUTUAL_FUND_DOMESTIC_ORDER_ACCEPT_CANCEL = 
        "CI80B";

    /** 
     * �O�����M������t	
     */
    public static final String MUTUAL_FUND_FOREIGN_ORDER_ACCEPT = "CI80C";

    /** 
     * �O�����M���������t	
     */
    public static final String MUTUAL_FUND_FOREIGN_ORDER_ACCEPT_CANCEL =
        "CI80D";

    /** 
     * ���M�抷������t	
     */
    public static final String MUTUAL_FUND_SWITCHING_ORDER_ACCEPT = "CI80E";

    /** 
     * ���M�抷���������t	
     */
    public static final String MUTUAL_FUND_SWITCHING_ORDER_ACCEPT_CANCEL =
        "CI80F";

    /** 
     * ���M�����抷�����ʒm	
     */
    public static final String MUTUAL_FUND_DEALING_SWITCHING_ORDER_NOTIFY =
        "CI811";

    /** 
     * �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���:�s�ꑗ�M����j
     */
    public static final String RUITO_REQUEST_MESSAGE_SEND_SELL_CANCEL = "DI804";

    /** 
     * �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�i���t:�s�ꑗ�M����j 
     */
    public static final String RUITO_REQUEST_MESSAGE_SEND_ORDER_CANCEL =
        "DI802";

    /** 
     * �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�iMRF���:�s�ꑗ�M����j 
     */
    public static final String RUITO_REQUEST_MESSAGE_SEND_MRF_ORDER_CANCEL =
        "GI832";

    /**
     * �i���o���e�[�u���̃f�[�^�R�[�h�jGI811�F��
     */
    public static final String AIO_INDIVIDUAL = "GI811";

    /**
     * �i���o���e�[�u���̃f�[�^�R�[�h�jFI811�F�{���ꊇ 
     */
    public static final String AIO_ALL_HEADQUARTERS = "FI811";

    /** 
     * GI80C�i���o���`�[��t�j 
     */
    public static final String AIO_SLIP_ACCEPT = "GI80C";

    /**
     * �i���o���`�[�L���[�e�[�u���̃f�[�^�R�[�h�jGI803 
     */
    public static final String AIO_SLIP_SERVE = "GI803";

    /**
     * �i�o�����������L���[�e�[�u���̃f�[�^�R�[�h�jGI801 
     */
    public static final String AIO_CASH_OUT_REQUEST_ORDER = "GI801";

    /**
     * �i�o��������t�L���[�e�[�u���̃f�[�^�R�[�h�jGI80A 
     */
    public static final String AIO_CASH_OUT_REQUEST_ACCEPT = "GI80A";

    /**
     * �i�U�֐��������L���[�e�[�u���̃f�[�^�R�[�h�jGI806(�ۏ؋��U�֐���) 
     */
    public static final String AIO_TRANSFER_REQUEST_ORDER = "GI806";

    /**
     * �i�U�֐�����t�L���[�e�[�u���̃f�[�^�R�[�h�jGI80F (�ۏ؋��U�֐�����t)
     */
    public static final String AIO_TRANSFER_REQUEST_ACCEPT = "GI80F";

    /**
     * �i�U�֓��͒ʒm�L���[�e�[�u���̃f�[�^�R�[�h�jGI812(�ۏ؋��U�֋����ʒm) 
     */
    public static final String AIO_TRANSFER_INPUT_NOTIFY = "GI812";
    
    /**
     * �i��p�U�֐����L���[�e�[�u���̃f�[�^�R�[�h�jGI807(��p�U�֐���)
     */
    public static final String AIO_MRGSEC_TRANSFER = "GI807";
 
    /**
     * �i��p�U�֋����L���[�e�[�u���̃f�[�^�R�[�h�jGI813(��p�U�֋����ʒm) 
     */
    public static final String AIO_MRGSEC_TRANS_NOTIFY = "GI813";
    
    /**
     * �i��p�U�֎�t�L���[�e�[�u���̃f�[�^�R�[�h�jGI80G (��p�U�֐�����t) 
     */
    public static final String AIO_MRGSEC_TRANS_ACCEPT = "GI80G";
    
    /**
     * GI821�F�ڋq�o�^
     */
    public static final String ACCOPEN_ACCOUNT_REGIST = "GI821";
 
    /**
     * GI827�F�_�񏑒���
     */
    public static final String ACCOPEN_CONTRACT_COLLECT = "GI827";
 
    /**
     * GI823�F�U�֐\���i��s�j
     */
    public static final String ACCOPEN_TRANSFER_BANK = "GI823";
 
    /**
     * GI828�F�U�֐\���i�X���j
     */
    public static final String ACCOPEN_TRANSFER_POSTAL = "GI828";
 
    /**
     * GI822�F�ېU����
     */
    public static final String ACCOPEN_AGREE_TRANSFER = "GI822";
 
    /**
     * GI837�F�L�����
     */
    public static final String ACCOPEN_CHARGED_INFO = "GI837";
 
    /**
     * GI825�FMRF����
     */
    public static final String ACCOPEN_MRF_ACCOUNT = "GI825";
 
    /**
     * GI842�F�Ïؔԍ�
     */
    public static final String ACCOPEN_PASSWORD = "GI842";

    /**
     * GI835�F��������U�֋����ʒm 
     */
    public static final String AIO_SPSEC_TRANS_NOTIFY = "GI835";

    /**
     * GI838�F�d�v����
     */
    public static final String ACCOPEN_IMPORTANT_ITEM = "GI838";
 
    /**
     * GI839�F�{�l�m�F
     */
    public static final String ACCOPEN_ID_CONFIRM = "GI839";
 
    /**
     * GI844�F��d�E�d�q��t�E�������
     */
    public static final String ACCOPEN_TRADE_CONDITION = "GI844";
 
    /**
     * GI819�F�����ғo�^�`�[
     */
    public static final String ACCOPEN_INSIDER_REG_VOUCHER = "GI819";
    
    /**
     * GI824�FGP�����o�^�`�[
     */
    public static final String ACCOPEN_GP_REG_VOUCHER = "GI824";
    
    /**
     * GI848�F�ڋq�������̓o�^�`�[
     */
    public static final String ACCOPEN_REALNAME_REG_VOUCHER = "GI848";
    
    /**
     * GI849�F���O���E����o�^�`�[
     */
    public static final String ACCOPEN_STOCKHOLDER_REG_VOUCHER = "GI849";
    
    /**
     * GI845�F�ڋq�o�^�i����Ɓj
     */
    public static final String ACCOPEN_REG_BROKERAGE = "GI845";
    
    /**
     * GI82A�F�ڋq�o�^��t
     */
    public static final String ACCOPEN_ACCOUNT_REGIST_ACCEPT = "GI82A";
 
    /**
     * GI82G�F�_�񏑒�����t
     */
    public static final String ACCOPEN_CONTRACT_COLLECT_ACCEPT = "GI82G";
 
    /**
     * GI82C�F�U�֐\���i��s�j��t
     */
    public static final String ACCOPEN_TRANSFER_BANK_ACCEPT = "GI82C";
 
    /**
     * GI82H�F�U�֐\���i�X���j��t
     */
    public static final String ACCOPEN_TRANSFER_POSTAL_ACCEPT = "GI82H";
 
    /**
     * GI82B�F�ېU���ӎ�t
     */
    public static final String ACCOPEN_AGREE_TRANSFER_ACCEPT = "GI82B";
 
    /**
     * GI83G�F�L������t
     */
    public static final String ACCOPEN_CHARGED_INFO_ACCEPT = "GI83G";
 
    /**
     * GI82E�FMRF������t
     */
    public static final String ACCOPEN_MRF_ACCOUNT_ACCEPT = "GI82E";
    
    /**
     * GI81I�F�����ғo�^��t
     */
    public static final String ACCOPEN_INSIDER_REG_ACCEPT = "GI81I";
    
    /**
     * GI82D�FGP�����o�^��t
     */
    public static final String ACCOPEN_GP_REG_ACCEPT = "GI82D";
    
    /**
     * GI84I�F���O���E�o�^��t
     */
    public static final String ACCOPEN_STOCKHOLDER_REG_ACCEPT = "GI84I";
    
    /**
     * GI84H�F�ڋq���̓o�^��t
     */
    public static final String ACCOPEN_REALNAME_REG_ACCEPT = "GI84H";
    
    /**
     * GI84E�F�ڋq�o�^�i����Ɓj��t
     */
    public static final String ACCOPEN_ACC_REG_ACCEPT = "GI84E";

    /**
     * GI851:�U�֐����`�[
     */
    public static final String AIO_REQUEST_SLIP = "GI851";    

    /**
     * GI852:�،��o�ɐ����`�[
     */
    public static final String SECURITIES_OUT_REQUEST_SLIP = "GI852";    

    /**
     * GI846:���b�N�q�o�^����
     */
    public static final String ACCINFO_LOCK_REGIST_CANCEL = "GI846";

    /**
     * GI847:Y�q�o�^����
     */
    public static final String ACCINFO_YELLOW_REGIST_CANCEL = "GI847";

    /**
     * GI84F:���b�N�q�o�^������t
     */
    public static final String ACCINFO_LOCK_REGIST_CANCEL_ACCEPT = "GI84F";

    /**
     * GI84G:Y�q�o�^������t
     */
    public static final String ACCINFO_YELLOW_REGIST_CANCEL_ACCEPT = "GI84G";

    /**
     * CI811:�������M-���M�����ʒm
     */
    public static final String MUTUAL_FUND_DOMESTIC_ORDER_NOTIFY = "CI811";

    /**
     * CI813:�O�����M-���M�����ʒm
     */
    public static final String MUTUAL_FUND_FOREIGN_ORDER_NOTIFY = "CI813";

    /**
     * CI817�F��W-���M�����ʒm
     */
    public static final String MUTUAL_FUND_RECRUIT_ORDER_NOTIFY = "CI817";
    
    /**
     * GI854�F�O�ݗa�������o�^�`�[
     */
    public static final String F_DEPOSIT_REG = "GI854";
    
    /**
     * GI853�F�O�ݓ��o��
     */
    public static final String FOREIGN_CASH_TRANSFER = "GI853";
    
    /**
     * GI804�F�O�ݓ��o���`�[
     */
    public static final String FOREIGN_CASH_TRANSFER_ORDER = "GI804";

    /**
     * GI80D�F�O�ݓ��o���`�[��t
     */
    public static final String F_CASH_TRANS_ORDER_ACCEPT = "GI80D";

    /**
     * GI85D�F�O�ݗa�������o�^��t
     */
    public static final String F_DEPOSIT_REG_ACCEPT = "GI85D";

    /**
     * DI821�F�O��MMF
     */
    public static final String FOREIGN_MMF = "DI821";

    /**
     * GI843:���E��c�d�q��t�E��������o�^
     */
    public static final String ACCOPEN_CONDITION_REGIST = "GI843";

    /**
     * GI84C�F���E��c�d�q��t�E��������o�^��t 
     */
    public static final String ACCOPEN_CONDITION_REG_ACCEPT = "GI84C";

    /**
     * AXQY1:���ӏ��i������j�ʒm
     */
    public final static String SELL_STOP_INFO = "AXQY1";

    /**
     * AXRY1:���ӏ��i�����l�����j�ʒm
     */
    public final static String LIMIT_RANGE_INFO = "AXRY1";

    /**
     * AXSY1:���ӏ��i�t���[�t�H�[�}�b�g�j�ʒm
     */
    public final static String FREE_FORMAT = "AXSY1";

    /**
     * GI865�F�@@�\�ʒm���o�^
     */
    public static final String ACCOPEN_AGENCY_INFO_REGIST = "GI865";

    /**
     * GI86E�F�@@�\�ʒm���o�^��t
     */
    public static final String ACCOPEN_AGENCY_INFO_REG_ACCEPT = "GI86E";
}
@
