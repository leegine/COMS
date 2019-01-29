head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityFrontOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������T�[�r�X(WEB3EquityFrontOrderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �������F (SRA) �V�K�쐬
Revesion History : 2007/12/17 �����F ���f�� 1243
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;

/**
 * (���������T�[�r�X)<BR>
 * <BR>
 * ���������T�[�r�X�̃C���^�t�F�[�X�B
 * @@version 1.0
 */
public interface WEB3EquityFrontOrderService extends Service
{
    /**
     * (get�����o�H�敪)<BR>
     * �����̊�����������I�u�W�F�N�g�����A�����\�Ȕ����o�H�敪���擾���ԋp����B<BR>
     * @@param l_tradedProduct - (�����������)<BR>
     * ������������I�u�W�F�N�g�B
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B
     * @@param l_strSonarTradedCode - (����R�[�h�iSONAR�j)<BR>
     * ����R�[�h�iSONAR�j�B<BR>
     * <BR>
     * 11�F���ʊ���<BR>
     * 16�F����O����<BR>
     * 51�F�M�p��<BR>
     * 52�F�M�p�ԍ�<BR>
     * 53�F�������n<BR>
     * 92�F��O���(��JASDAQ)
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getSubmitOrderRouteDiv(
        EqTypeTradedProduct l_tradedProduct,
        String l_strInstitutionCode,
        String l_strSonarTradedCode)
        throws WEB3BaseException;
    
    /**
     * (get��������������o�H�敪)<BR>
     * �����̒�������Ώۂ̒����P�ʃI�u�W�F�N�g���A�����\�Ȕ����o�H�敪���擾���ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeSubmitOrderRouteDiv(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (get������ؑ�)<BR>
     * �w��̒����P�ʂɍ��v���锭����ؑփI�u�W�F�N�g���擾���Ԃ��B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return ������ؑ�
     * @@throws WEB3BaseException
     */
    public WEB3GentradeOrderSwitching getOrderSwitching(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (get�t�����g�����V�X�e���敪)<BR>
     * �����̎s��R�[�h�y�ѓX�����J�敪���A�t�����g�����V�X�e���敪���擾���ԋp����B<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * Web�V�̎s��R�[�h�B
     * @@param l_strOpenOtcDiv - (�X�����J�敪)<BR>
     * �X�����J�敪�B<BR>
     * �i0�FDEFAULT�@@1�F�o�^�@@3�F�}�[�P�b�g���C�N�����j 
     * @@return String
     */
    public String getFrontOrderSystemCode(
        String l_strMarketCode,
        String l_strOpenOtcDiv);
    
    /**
     * (get�t�����g����������敪�R�[�h )<BR>
     * �����̎s��R�[�h���A�t�����g����������敪�R�[�h���擾���ԋp����B<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * Web�V�̎s��R�[�h�B
     * @@return String
     */
    public String getFrontOrderExchangeCode(String l_strMarketCode);
    
    /**
     * (get�Г���������)<BR>
     * �����̒����P�ʃI�u�W�F�N�g���A�����Ɏg�p����u�Г��������ځv�ݒ�p�������<BR>
     * �擾���ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getCorpCode(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (get�i������j�Г���������)<BR>
     * �����̒����P�ʃI�u�W�F�N�g���A�����Ɏg�p����u�i������j�Г��������ځv�ݒ�p�������<BR>
     * �擾���ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getOrgCorpCode(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (get����Rev�J�n�ʒuIN�Г���������)<BR>
     * �Г��������ڂ̕����񒆂́A����Rev.�J�n�ʒu��Ԃ��B<BR>
     * @@return int
     */
    public int getIndexOfOrderRevInCorpCode();
    
    /**
     * (get����Rev����)<BR>
     * ����Rev.�̌�����Ԃ��B<BR>
     * @@return int
     */
    public int getFigureOfOrderRev();
    
    /**
     * (get����������Rev)<BR>
     * �����̒����㒍���P�ʃI�u�W�F�N�g���A<BR>
     * �������ɒ����P�ʃe�[�u��.����Rev�ɐݒ肷�镶������擾���Ԃ��B<BR>
     * @@param l_orderUnit - (�����㒍���P��)<BR>
     * ������̒����P�ʃI�u�W�F�N�g�B<BR>
     * �ixTrade�W�����ڂɁA������̒l���ݒ肳��Ă���I�u�W�F�N�g�j<BR>
     * ���X�V�C���^�Z�v�^.mutate()��������R�[������邱�Ƃ�O��Ƃ��Ă���B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeOrderRev(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (get������MQ�f�[�^�R�[�h)<BR>
     * �����̔����o�H�敪���A�������Ɏg�p����MQ�f�[�^�R�[�h�ݒ�p�������<BR>
     * �擾���ԋp����B<BR>
     * @@param l_strSubmitOrderRouteDiv - (�����o�H�敪)<BR>
     * �����o�H�敪�B
     * @@return String
     */
    public String getOrderMQDataCode(String l_strSubmitOrderRouteDiv);
    
    /**
     * (get���������MQ�f�[�^�R�[�h)<BR>
     * �����̔����o�H�敪���A����������Ɏg�p����MQ�f�[�^�R�[�h�ݒ�p�������<BR>
     * �擾���ԋp����B<BR>
     * @@param l_strSubmitOrderRouteDiv - (�����o�H�敪)<BR>
     * �����o�H�敪�B
     * @@return String
     */
    public String getChangeCancelMQDataCode(String l_strSubmitOrderRouteDiv);
    
    /**
     * (is�s��ʒm������IN�x�e���ԑ�)<BR>
     * ��������x�e���Ԓ��̎��ԑтɂ����āA<BR>
     * �w��̒����Ɋ֌W����f�[�^�i�����A������܂ށj���s��ɒʒm���ł��邩�ǂ�����<BR>
     * ������������L���[�e�[�u���̃f�[�^��蔻�肵�Ԃ��B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isMarketNotifyingOrderInBreakTimeZone(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (lock������������L���[)<BR>
     * �w��̒����P�ʂ̊�����������L���[�f�[�^�ɋ��L���b�N��������B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    public void lockHostEqtypeOrderAll(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (get������������L���[)<BR>
     * �w��̒����P�ʂɊY�����銔����������L���[���擾���Ԃ��B<BR>
     * �Y������f�[�^�����݂��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return HostEqtypeOrderAllParams
     * @@throws WEB3BaseException
     */
    public HostEqtypeOrderAllParams getHostEqtypeOrderAll(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (update������������L���[AT��t���ԊO)<BR>
     * �O���t���ԊO�������Ĕ������邽�߂̃L���[�f�[�^�X�V���s���B
     * @@param l_orderUnitAfter - (�����P�ʁi�X�V��j)<BR>
     * �X�V��̒����P�ʃI�u�W�F�N�g�B
     * @@param l_orderUnitBefore - (�����P�ʁi�X�V�O�j)<BR>
     * �X�V�O�̒����P�ʃI�u�W�F�N�g�B
     * @@param l_blnIsCancel - (is���)<BR>
     * ������ǂ����𔻒肷��t���O�B<BR>
     * �itrue�F����Afalse�F����ȊO�j
     * @@throws WEB3BaseException
     */
    public void updateHostEqtypeOrderAllAtAcceptOvertime(
        EqTypeOrderUnit l_orderUnitAfter,
        EqTypeOrderUnit l_orderUnitBefore,
        boolean l_blnIsCancel)
        throws WEB3BaseException;
    
    /**
     * (getNext����Rev)<BR>
     * �����̒���Rev��1���Z�����l��Ԃ��B<BR>
     * @@param l_strOrderRev - (����Rev)<BR>
     * ������̒����P�ʃI�u�W�F�N�g�B<BR>
     * @@return String
     * @@throws WEB3BusinessLayerException
     */
    public String getNextOrderRev(String l_strOrderRev) throws WEB3BusinessLayerException;
    
    /**
     * (getPTS����������Rev )<BR>
     * �����̒����㒍���P�ʃI�u�W�F�N�g���A <BR> 
     * �������ɒ����P�ʃe�[�u��.����Rev�ɐݒ肷�镶������擾���Ԃ��B  <BR>
     * �iPTS�����̏ꍇ�ɃR�[�������B�j<BR>
     * @@param l_orderUnit - (�����㒍���P��)<BR>
     * ������̒����P�ʃI�u�W�F�N�g�B  <BR>
     * �ixTrade�W�����ڂɁA������̒l���ݒ肳��Ă���I�u�W�F�N�g�j  <BR>
     * ���X�V�C���^�Z�v�^.mutate()��������R�[������邱�Ƃ�O��Ƃ��Ă���B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getPTSChangeOrderRev(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
}
@
