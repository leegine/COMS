head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.22.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackBranchImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3PreLoaderCallbackBranchImpl�N���X(WEB3PreLoaderCallbackBranchImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/24 �R�c�@@��i (FLJ) �V�K�쐬
 */
package webbroker3.preloader.stdimpls;

import webbroker3.preloader.WEB3DefaultPreLoaderCallback;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

/**
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3PreLoaderCallbackBranchImpl
    extends WEB3DefaultPreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3DefaultPreLoaderCallback#load(com.fitechlabs.dbind.Row)
     */
    protected void load(Row l_row) throws DataException
    {
        BranchRow l_branchRow = (BranchRow) l_row;
        BranchDao.findRowByInstitutionCodeBranchCode(
            l_branchRow.getInstitutionCode(),
            l_branchRow.getBranchCode());
    }

}
@
