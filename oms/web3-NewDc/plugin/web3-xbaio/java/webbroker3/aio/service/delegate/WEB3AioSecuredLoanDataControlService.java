head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.13.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecuredLoanDataControlService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���f�[�^����T�[�r�X(WEB3AioSecuredLoanDataControlService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 �đo�g (���u) �V�K�쐬 �d�l�ύX ���f��No.755�ANo.761�ANo.776�ANo.788�ANo.796
*/

package webbroker3.aio.service.delegate;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.StockSecuredLoanParams;

/**
 * (�،��S�ۃ��[���f�[�^����T�[�r�X)<BR>
 * �،��S�ۃ��[���f�[�^����T�[�r�X�C���^�[�t�F�C�X�B<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public interface WEB3AioSecuredLoanDataControlService extends Service
{
    /**
     * (insert�����S�ۃ��[��)<BR>
     * �����S�ۃ��[�������e�[�u����insert�������s���B<BR>
     * <BR>
     * @@param l_strStockLoanAccNumber - (�X�g�b�N���[�������ԍ�)<BR>
     * �X�g�b�N���[�������ԍ�<BR>
     * @@param l_mainAccountParams - (�ڋqParams)<BR>
     * �ڋqParams<BR>
     * @@throws WEB3BaseException
     */
    public void insertStockSecuredLoan(String l_strStockLoanAccNumber, MainAccountParams l_mainAccountParams)
        throws WEB3BaseException;

    /**
     * (get�����S�ۃ��[���ڋq���)<BR>
     * �����S�ۃ��[�������I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_lngAccId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_tsDate - (���t)<BR>
     * YYYYMMDD<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getStockSecuredLoanAccInfo(long l_lngAccId, Timestamp l_tsDate) throws WEB3BaseException;

    /**
     * (get�����S�ۃ��[���ꗗ)<BR>
     * �w�肳�ꂽ�����ɍ��v���銔���S�ۃ��[�������e�[�u�����������A<BR>
     * ���̌��ʂ������S�ۃ��[��Params�I�u�W�F�N�g�̔z��ɂ��ĕԋp����B<BR>
     * <BR>
     * @@param l_stockSecuredLoans - (�����S�ۃ��[���z��[])<BR>
     * �����S�ۃ��[���z��[]<BR>
     * @@return StockSecuredLoanParams[]
     * @@throws WEB3BaseException
     */
    public StockSecuredLoanParams[] getStockSecuredLoanList(Object[] l_stockSecuredLoans) throws WEB3BaseException;

    /**
     * (update�̔ԃe�[�u��)<BR>
     * �̔ԃe�[�u����update�������s��<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strSerialNumberName - (�̔ԍ��ږ�)<BR>
     * �̔ԍ��ږ�<BR>
     * @@param l_strSerialNumber - (�V���A���i���o�[)<BR>
     * �V���A���i���o�[<BR>
     * @@throws WEB3BaseException
     */
    public void updateCommSerialNumbers(
        String l_strInstitutionCode,
        String l_strSerialNumberName,
        String l_strSerialNumber) throws WEB3BaseException;
}
@
