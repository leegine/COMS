head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.19.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	PtsOrderexecutionEndDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link PtsOrderexecutionEndDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link PtsOrderexecutionEndRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PtsOrderexecutionEndPK 
 * @@see PtsOrderexecutionEndRow 
 */
public class PtsOrderexecutionEndDao extends DataAccessObject {


  /** 
   * ����{@@link PtsOrderexecutionEndDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private PtsOrderexecutionEndRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link PtsOrderexecutionEndRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link PtsOrderexecutionEndDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link PtsOrderexecutionEndDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link PtsOrderexecutionEndRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PtsOrderexecutionEndRow )
                return new PtsOrderexecutionEndDao( (PtsOrderexecutionEndRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PtsOrderexecutionEndRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PtsOrderexecutionEndRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link PtsOrderexecutionEndRow}�I�u�W�F�N�g 
    */
    protected PtsOrderexecutionEndDao( PtsOrderexecutionEndRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link PtsOrderexecutionEndRow}�I�u�W�F�N�g���擾���܂��B
   */
    public PtsOrderexecutionEndRow getPtsOrderexecutionEndRow() {
        return row;
    }


  /** 
   * �w���{@@link PtsOrderexecutionEndRow}�I�u�W�F�N�g����{@@link PtsOrderexecutionEndDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link PtsOrderexecutionEndRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link PtsOrderexecutionEndDao}�擾�̂��߂Ɏw���{@@link PtsOrderexecutionEndRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link PtsOrderexecutionEndDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static PtsOrderexecutionEndDao forRow( PtsOrderexecutionEndRow row ) throws java.lang.IllegalArgumentException {
        return (PtsOrderexecutionEndDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PtsOrderexecutionEndRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link PtsOrderexecutionEndRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link PtsOrderexecutionEndPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link PtsOrderexecutionEndParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PtsOrderexecutionEndRow.TYPE );
    }


  /** 
   * {@@link PtsOrderexecutionEndRow}����ӂɓ��肷��{@@link PtsOrderexecutionEndPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link PtsOrderexecutionEndRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link PtsOrderexecutionEndParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link PtsOrderexecutionEndPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static PtsOrderexecutionEndPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link PtsOrderexecutionEndRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link PtsOrderexecutionEndRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static PtsOrderexecutionEndRow findRowByPk( String p_institutionCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        PtsOrderexecutionEndPK pk = new PtsOrderexecutionEndPK( p_institutionCode, p_marketCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���PtsOrderexecutionEndPK�I�u�W�F�N�g����{@@link PtsOrderexecutionEndRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����PtsOrderexecutionEndPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link PtsOrderexecutionEndRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static PtsOrderexecutionEndRow findRowByPk( PtsOrderexecutionEndPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PtsOrderexecutionEndRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String)}�����{@@link #forRow(PtsOrderexecutionEndRow)}���g�p���Ă��������B 
   */
    public static PtsOrderexecutionEndDao findDaoByPk( String p_institutionCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        PtsOrderexecutionEndPK pk = new PtsOrderexecutionEndPK( p_institutionCode, p_marketCode );
        PtsOrderexecutionEndRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(PtsOrderexecutionEndPK)}�����{@@link #forRow(PtsOrderexecutionEndRow)}���g�p���Ă��������B 
   */
    public static PtsOrderexecutionEndDao findDaoByPk( PtsOrderexecutionEndPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PtsOrderexecutionEndRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_marketCode, and �ɂĎw��̒l�����ӂ�{@@link PtsOrderexecutionEndRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_marketCode, and �̒l�ƈ�v����{@@link PtsOrderexecutionEndRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static PtsOrderexecutionEndRow findRowByInstitutionCodeMarketCode( String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PtsOrderexecutionEndRow.TYPE,
            "institution_code=? and market_code=?",
            null,
            new Object[] { p_institutionCode, p_marketCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PtsOrderexecutionEndRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PtsOrderexecutionEndDao.findRowsByInstitutionCodeMarketCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeMarketCode(String, String)}�����{@@link #forRow(PtsOrderexecutionEndRow)}���g�p���Ă��������B 
   */
    public static PtsOrderexecutionEndDao findDaoByInstitutionCodeMarketCode( String p_institutionCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeMarketCode( p_institutionCode, p_marketCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
