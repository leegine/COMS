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
filename	WEB3EquityOrderCarryOverSkipUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������J�z�X�L�b�v�����ʒm�ꌏ�T�[�r�X(WEB3EquityOrderCarryOverSkipPartService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 羐� (���u) �V�K�쐬
                   2004/12/13 �������F(SRA) �c�Č��Ή��ɂ��X�V
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEquityCarryoverSkipParams;

/**
 * �i���������J�z�X�L�b�v�����ʒm�ꌏ�T�[�r�X�j�B<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@version 1.0
 */
public interface WEB3EquityOrderCarryOverSkipUnitService extends Service
{

    /**
     * (�����J�z���ʍX�V)<BR>
     * �X�L�b�v�����̓o�^�^�o�^�����ʒm���e�A<BR>
     * �y�ђ����J�z�����̎��s�󋵁i�������^�����ρj�ɂ��A<BR>
     * �K�v�ȌJ�z�������ʂ̍X�V���s���B<BR>
     * @@param l_hostEquityCarryoverSkipParams 
     * �����J�z�X�L�b�v�����ʒm�L���[Params<BR>
     * @@param l_listOrderexecutionEndParams 
     * @@throws WEB3BaseException
     * @@roseuid 4137CDDA02D6
     */
    public void updateOrderCarryOverResult(HostEquityCarryoverSkipParams l_hostEquityCarryoverSkipParams,List l_listOrderexecutionEndParams )
        throws WEB3BaseException;
}
@
