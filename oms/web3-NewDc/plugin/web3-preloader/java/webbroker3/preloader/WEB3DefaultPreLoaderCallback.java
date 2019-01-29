head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.20.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3DefaultPreLoaderCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3DefaultPreLoaderCallback�N���X(WEB3DefaultPreLoaderCallback.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/21 �R�c�@@��i (FLJ) �V�K�쐬
 */
package webbroker3.preloader;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * WEB3PreLoaderCallback�C���^�[�t�F�[�X�̃f�t�H���g�����N���X<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public abstract class WEB3DefaultPreLoaderCallback implements WEB3PreLoaderCallback
{
    
    /**
     * ���̎����N���X�ł́AQueryProcessor#doFindByPrimaryKey(PrimaryKey)�ɂ��
     * PrimaryKey�ł̌������s���B<BR>
     * PrimaryKey�ɂ́A�p�����[�^�Ŏw�肳�ꂽRow�I�u�W�F�N�g��PrimaryKey��
     * �ݒ肷��B<BR>
     * PrimaryKey�ł̌��������s��AWEB3DefaultPreLoaderCallback#load(Row)���\�b�h��
     * ���s����B
     * 
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#process(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
        
        PrimaryKey l_pk = l_row.getPrimaryKey();
        QueryProcessor l_qp = Processors.getDefaultProcessor();
        l_qp.doFindByPrimaryKeyQuery(l_pk);
        
		load(l_row);
        
    }
    
    protected abstract void load(Row l_row) throws DataException;
    
}@
