head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.22.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackOrderAcceptStatusImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3PreLoaderCallbackOrderAcceptStatusImpl�N���X(WEB3PreLoaderCallbackOrderAcceptStatusImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/01 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.preloader.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;

import webbroker3.gentrade.data.OrderAcceptStatusDao;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.preloader.WEB3PreLoaderCallback;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoaderCallbackOrderAcceptStatusImpl implements
        WEB3PreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#process(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
        OrderAcceptStatusRow l_oasRow = (OrderAcceptStatusRow) l_row;
        OrderAcceptStatusDao.findRowByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction(
            l_oasRow.getInstitutionCode(), 
            l_oasRow.getBranchCode(), 
            l_oasRow.getOrderAccProduct(), 
            l_oasRow.getOrderAccTransaction());
    }

}
@
